package io.bz.data.repository

import io.bz.data.lib.TdClientManager
import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.repository.AuthRepository
import io.bz.domain.state.AuthState
import io.bz.domain.stores.AuthStore
import kotlinx.coroutines.flow.StateFlow
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

class AuthRepositoryImpl(
    store: AuthStore,
    private val clientManager: TdClientManager,
) : AuthRepository {
    override val state: StateFlow<AuthState> = store.state


    override suspend fun sendSetTdlibParameters(intent: AuthIntent.SendTDLibParameters): DomainResult<Unit> {
        val appId = 35865640
        val appHash = "7540cf3f1b1974d68118bfa0052e62ca"
        // Путь к базе данных (на iOS это обычно папка Documents)

        val fileManager = NSFileManager.defaultManager
        val documentsUrl =
            fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask).first() as NSURL
        val documentsPath = documentsUrl.path ?: "tdlib3"
        val databasePath = "$documentsPath/tdlib_db3"

        val filesPath = "$documentsPath/tdlib_files3" // Отдельная папка для файлов

        val jsonQuery = """
        {
          "@type": "setTdlibParameters",
          "database_directory": "$databasePath",
          "files_directory": "$filesPath",
          "use_file_database": true,
          "use_chat_info_database": true,
          "use_message_database": true,
          "use_secret_chats": true,
          "use_test_dc": false,
          "api_id": $appId,
          "api_hash": "$appHash",
          "system_language_code": "en",
          "device_model": "iPhone 13 Pro Max",
          "system_version": "17.4",
          "application_version": "1.0.6",
          "enable_storage_optimizer": true
        }
        """.trimIndent()
        return clientManager.send(jsonQuery)
    }

    override suspend fun sendPhone(phoneNumber: String): DomainResult<Unit> {
        val jsonQuery = """
        {
            "@type": "setAuthenticationPhoneNumber",
            "phone_number": "$phoneNumber"
        }
        """.trimIndent()
        return clientManager.send(jsonQuery)
    }

    override suspend fun sendsSMSCode(code: String): DomainResult<Unit> {
        val jsonQuery = """
        {
            "@type": "checkAuthenticationCode",
            "code": "$code"
        }
        """.trimIndent()
        return clientManager.send(jsonQuery)
    }

    override suspend fun sendPassword(password: String): DomainResult<Unit> {
        val jsonQuery = """
        {
            "@type": "checkAuthenticationPassword",
            "password": "$password"
        }
        """.trimIndent()
        return clientManager.send(jsonQuery)
    }

    override suspend fun sendEmail(email: String): DomainResult<Unit> {
        val jsonQuery = """
        {
            "@type": "setAuthenticationEmailAddress",
            "email_address": "$email"
        }
        """.trimIndent()
        return clientManager.send(jsonQuery)
    }

    override suspend fun sendLogout(): DomainResult<Unit> {
        val jsonQuery = """
        {
            "@type": "logOut"
        }
        """.trimIndent()
        return clientManager.send(jsonQuery)
    }

    override suspend fun sendClose(): DomainResult<Unit> {
        val jsonQuery = """
        {
            "@type": "close"
        }
        """.trimIndent()
        return clientManager.send(jsonQuery)
    }

    override suspend fun recreate(): DomainResult<Unit> {
        clientManager.recreateClient()
        return DomainResult.Success(Unit)
    }

    override suspend fun resendSmsCode(): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

}