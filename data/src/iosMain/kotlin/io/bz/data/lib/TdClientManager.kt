package io.bz.data.lib

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdatesProcessor
import kotlinx.cinterop.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import org.tdlib.native.*
import kotlin.concurrent.AtomicReference

@OptIn(ExperimentalForeignApi::class)
class TdClientManager(
    private val scope: CoroutineScope,
    private val processor: TdUpdatesProcessor,
) {
    // Храним указатель на нативный клиент
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
        // Если старый клиент существует, его нужно закрыть (в TDLib через спец. запрос)
        // Для простоты здесь создаем новый
        val newClient = td_json_client_create()
        clientPtr.value = newClient

        // Запускаем цикл прослушивания обновлений (аналог ResultHandler в Android)
        scope.launch(Dispatchers.Default) {
            while (isActive) {
                val currentClient = clientPtr.value ?: break
                
                // Ждем обновления от TDLib (timeout 1.0 сек)
                val responsePtr = td_json_client_receive(currentClient, 1.0)
                
                if (responsePtr != null) {
                    val jsonString = responsePtr.toKString()
                    // На iOS мы получаем JSON строку. 
                    // Вам нужно преобразовать её в TdApi.Object (или вашу обертку)
                    // Обычно это делается через какой-то маппер или kotlinx.serialization
                    _eventQueue.tryEmit(TdNativeObjectWrapper(jsonString))
                }
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    fun send(jsonQuery: String) {
        val currentClient = clientPtr.value ?: return

        scope.launch(Dispatchers.Default) {
            // Если cinterop ожидает String, мы передаем её напрямую.
            // Kotlin/Native сам сделает конвертацию в UTF-8.
            try {
                println("NATIVE_SENDING: $jsonQuery")
                td_json_client_send(currentClient, jsonQuery)
            } catch (e: Exception) {
                println("NATIVE_SEND_ERROR: ${e.message}")
            }
        }
    }

    // Вспомогательная функция (заглушка сериализации)
    private fun serializeRequest(request: Any): String {
        // TDLib на iOS работает ТОЛЬКО с JSON строками.
        // Вам нужно использовать какой-то конвертер, так как TdApi.Function 
        // в iOS-библиотеке не имеет метода .toJson() автоматически.
        return "" 
    }
}