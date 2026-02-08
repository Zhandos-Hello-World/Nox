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
): AuthRepository {
    override val state: StateFlow<AuthState> = store.state


    override suspend fun sendSetTdlibParameters(intent: AuthIntent.SendTDLibParameters): DomainResult<Unit> {
        val appId = 35865640
        val appHash = "7540cf3f1b1974d68118bfa0052e62ca"
        // Путь к базе данных (на iOS это обычно папка Documents)

        val fileManager = NSFileManager.defaultManager
        val documentsUrl = fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask).first() as NSURL
        val documentsPath = documentsUrl.path ?: "tdlib"
        val databasePath = "$documentsPath/tdlib_db"

        val jsonQuery = """
        {
          "@type": "setTdlibParameters",
          "database_directory": "$databasePath",
          "use_message_database": true,
          "use_secret_chats": true,
          "api_id": $appId,
          "api_hash": "$appHash",
          "system_language_code": "en",
          "device_model": "iPhone",
          "application_version": "1.0"
        }
    """.trimIndent()

        clientManager.send(jsonQuery)

        return DomainResult.Success(Unit)
    }

    override suspend fun sendPhone(phoneNumber: String): DomainResult<Unit> {
        val json = """
        {
            "@type": "setAuthenticationPhoneNumber",
            "phone_number": "$phoneNumber"
        }
    """.trimIndent()
        clientManager.send(json)
        return DomainResult.Success(Unit)
    }

    override suspend fun sendsSMSCode(code: String): DomainResult<Unit> {
        val json = """
        {
            "@type": "checkAuthenticationCode",
            "code": "$code"
        }
    """.trimIndent()
        clientManager.send(json)
        return DomainResult.Success(Unit)
    }

    override suspend fun sendPassword(password: String): DomainResult<Unit> {
        val json = """
        {
            "@type": "checkAuthenticationPassword",
            "password": "$password"
        }
    """.trimIndent()
        clientManager.send(json)
        return DomainResult.Success(Unit)
    }

    override suspend fun sendEmail(email: String): DomainResult<Unit> {
        val json = """
        {
            "@type": "setAuthenticationEmailAddress",
            "email_address": "$email"
        }
    """.trimIndent()
        clientManager.send(json)
        return DomainResult.Success(Unit)
    }

    override suspend fun sendLogout(): DomainResult<Unit> {
        val json = """
        {
            "@type": "logOut"
        }
    """.trimIndent()
        clientManager.send(json)
        return DomainResult.Success(Unit)
    }

    override suspend fun sendClose(): DomainResult<Unit> {
        val json = """
        {
            "@type": "close"
        }
    """.trimIndent()
        clientManager.send(json)
        return DomainResult.Success(Unit)
    }

    override suspend fun recreate(): DomainResult<Unit> {
        clientManager.recreateClient()
        return DomainResult.Success(Unit)
    }
}