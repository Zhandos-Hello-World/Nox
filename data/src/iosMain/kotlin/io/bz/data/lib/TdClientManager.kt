package io.bz.data.lib

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdatesProcessor
import io.bz.domain.core.DomainError
import io.bz.domain.core.DomainResult
import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.tdlib.native.td_json_client_create
import org.tdlib.native.td_json_client_receive
import org.tdlib.native.td_json_client_send
import kotlin.concurrent.AtomicReference

@OptIn(ExperimentalForeignApi::class)
class TdClientManager(
    private val scope: CoroutineScope,
    private val processor: TdUpdatesProcessor,
) {
    private val clientPtr = AtomicReference<CPointer<out CPointed>?>(null)

    private val _eventQueue = MutableSharedFlow<TdNativeObjectWrapper>(
        extraBufferCapacity = Int.MAX_VALUE,
        replay = 1,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )

    init {
        // Подписываем процессор на очередь событий
        _eventQueue.onEach { processor.onNewUpdates(it) }.launchIn(scope)

        recreateClient()
    }

    fun recreateClient() {
        val newClient = td_json_client_create()
        clientPtr.value = newClient

        scope.launch(Dispatchers.Default) {
            while (isActive) {
                val currentClient = clientPtr.value ?: break

                val responsePtr = td_json_client_receive(currentClient, 1.0)

                if (responsePtr != null) {
                    val jsonString = responsePtr.toKString()
                    _eventQueue.tryEmit(TdNativeObjectWrapper(jsonString))
                }
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    suspend fun send(jsonQuery: String): DomainResult<Unit> {
        val currentClient = clientPtr.value ?: return DomainResult.Failure(
            DomainError.Unauthorized,
        )
        return try {
            println("NATIVE_SENDING: $jsonQuery")
            td_json_client_send(currentClient, jsonQuery)
            DomainResult.Success(Unit)
        } catch (e: Exception) {
            println("NATIVE_SEND_ERROR: ${e.message}")
            DomainResult.Failure(
                DomainError.Unknown(e.message ?: "Unknown error"),
            )
        }

    }
}