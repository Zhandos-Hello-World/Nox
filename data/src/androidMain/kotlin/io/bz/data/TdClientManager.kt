package io.bz.data

import io.bz.data.lib.Client
import io.bz.data.lib.TdApi
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