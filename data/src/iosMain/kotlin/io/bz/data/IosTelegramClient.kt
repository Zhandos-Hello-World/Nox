package io.bz.data

class IosTelegramClient : TelegramClient {

    override fun sendRequest(json: String) {
        // Здесь используем функции из вашего cinterop пакета
        // td_json_client_send(client, json)
    }
}