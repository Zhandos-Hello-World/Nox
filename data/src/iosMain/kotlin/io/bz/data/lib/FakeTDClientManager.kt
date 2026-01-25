package io.bz.data.lib

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdatesProcessor
import io.bz.data.handlers.AuthUpdateHandler
import io.bz.data.handlers.UsersUpdateHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FakeTDClientManager(
    private val scope: CoroutineScope,
    private val processor: TdUpdatesProcessor,
) {
    private val _eventQueue = MutableSharedFlow<TdNativeObjectWrapper>(
        extraBufferCapacity = Int.MAX_VALUE,
        replay = 1,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )

    init {
        _eventQueue.onEach { processor.onNewUpdates(it) }.launchIn(scope)
        sendReady()
    }

    fun sendReady() {
        println("started")
        scope.launch {
            delay(1000L)

            processor.handlers.forEach { handler ->
                if (handler is AuthUpdateHandler) {
//                    handler.readyState()
                    println("found and changed")
                }
            }
            _eventQueue.tryEmit(TdNativeObjectWrapper())
            delay(1000L)

            processor.handlers.forEach { handler ->
                if (handler is UsersUpdateHandler) {
                    handler.readyTestUser()
                    println("found and changed")

                }
            }

            _eventQueue.tryEmit(TdNativeObjectWrapper())
        }
    }

    fun recreateClient() {
        //TODO: implement
//        client = Client.create(
//            { obj ->
//                _updates.tryEmit(obj)
//            },
//            null,
//            null,
//        )
    }
}