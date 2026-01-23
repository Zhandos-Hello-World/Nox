package io.bz.data.lib

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdatesProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi

class TdClientManager(
    scope: CoroutineScope,
    private val processor: TdUpdatesProcessor,
) {
    private val _eventQueue = MutableSharedFlow<TdNativeObjectWrapper>(
        extraBufferCapacity = Int.MAX_VALUE,
        replay = 1,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )

    init {
        _eventQueue.onEach { processor.onNewUpdates(it) }.launchIn(scope)
    }

    var client = Client.create(
        {
            _eventQueue.tryEmit(TdNativeObjectWrapper(it))
        },
        null,
        null,
    )

    fun <T : TdApi.Object> send(request: TdApi.Function<T>, resultHandler: Client.ResultHandler) {
//        client.send(request, resultHandler)
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