package io.bz.data.lib

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class TdClientManager {
    private val _updates = MutableSharedFlow<TdApi.Object>(extraBufferCapacity = 100)
    val updates = _updates.asSharedFlow()

    var client: Client = Client.create(
        { obj ->
            _updates.tryEmit(obj)
        },
        null,
        null,
    )

    fun <T : TdApi.Object> send(request: TdApi.Function<T>, resultHandler: Client.ResultHandler) {
        client.send(request, resultHandler)
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