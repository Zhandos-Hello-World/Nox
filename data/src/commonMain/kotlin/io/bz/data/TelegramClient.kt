package io.bz.data

interface TelegramClient {
    fun sendRequest(json: String)
}