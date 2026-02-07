package io.bz.data

import kotlinx.cinterop.ExperimentalForeignApi
import org.tdlib.native.*

class TdLibCheck {
    @OptIn(ExperimentalForeignApi::class)
    fun runTest() {
        println("TDLib: Попытка создания клиента...")
        try {
            val clientId = td_json_client_create()
            println("TDLib: Клиент успешно создан, ID: $clientId")
        } catch (e: Exception) {
            println("TDLib: Ошибка при вызове метода: ${e.message}")
        }
    }
}