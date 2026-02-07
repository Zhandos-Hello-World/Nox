package io.bz.data

import kotlinx.cinterop.ExperimentalForeignApi
import org.tdlib.native.* // Пакет из твоего .def файла

class NativeTdLib {
    @OptIn(ExperimentalForeignApi::class)
    fun createClient() {
        // Эти функции должны стать доступными
        val client = td_json_client_create()
    }
}