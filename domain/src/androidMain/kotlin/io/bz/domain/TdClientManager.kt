package io.bz.domain

import io.bz.domain.lib.Client
import io.bz.domain.lib.TdApi

class TdClientManager(
) {
    var client: Client



    var updatesHandler = UpdatesHandler(
        onState = {
            return@UpdatesHandler
        },
    )


    init {
        client = Client.create(
            updatesHandler,
            null,
            null,
        )
    }

    fun recreateClient() {
        client = Client.create(
            updatesHandler,
            null,
            null,
        )
    }


    class UpdatesHandler(
        private val onState: (TdApi.Object) -> Unit,
    ) : Client.ResultHandler {
        override fun onResult(`object`: TdApi.Object) {
            onState.invoke(`object`)
        }
    }
}