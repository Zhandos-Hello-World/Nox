package io.bz.data.repostiory

import io.bz.data.TdClientManager
import io.bz.data.core.tdLibUnitCall
import io.bz.data.lib.TdApi
import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.repository.AuthRepository

class AuthRepositoryImpl(
    val clientManager: TdClientManager,
): AuthRepository {
    val client = clientManager.client

    override suspend fun sendSetTdlibParameters(intent: AuthIntent.SendTDLibParameters): DomainResult<Unit> {
        val appId = 0
        val appHash = "BuildConfig.API_HASH"

        val parameters = TdApi.SetTdlibParameters(
            intent.useTestServer,
            intent.databaseDirectory,
            intent.filesDirectory,
            intent.databaseEncryptionKey,
            intent.useFileDatabase,
            intent.useChatInfoDatabase,
            intent.useMessageDatabase,
            intent.useSecretChats,
            appId,
            appHash,
            intent.systemLanguageCode,
            intent.deviceModel,
            intent.systemVersion,
            intent.applicationVersion
        )
        return tdLibUnitCall(
            client = client,
            request = parameters,
        )
    }

    override suspend fun sendPhone(phoneNumber: String): DomainResult<Unit> {
        return tdLibUnitCall(
            client = client,
            request = TdApi.SetAuthenticationPhoneNumber(phoneNumber, null),
        )
    }

    override suspend fun sendsSMSCode(code: String): DomainResult<Unit> {
        return tdLibUnitCall(
            client = client,
            request = TdApi.CheckAuthenticationCode(code),
        )
    }

    override suspend fun sendPassword(password: String): DomainResult<Unit> {
        return tdLibUnitCall(
            client = client,
            request = TdApi.CheckAuthenticationPassword(password),
        )
    }

    override suspend fun sendEmail(email: String): DomainResult<Unit> {
        return tdLibUnitCall(
            client = client,
            request = TdApi.CheckAuthenticationPassword(email)
        )
    }

    override suspend fun sendLogout(): DomainResult<Unit> {
        return tdLibUnitCall(
            client = client,
            request = TdApi.LogOut(),
        )
    }

    override suspend fun sendClose(): DomainResult<Unit> {
        return tdLibUnitCall(
            client = client,
            request = TdApi.Close(),
        )
    }

    override suspend fun recreate(): DomainResult<Unit> {
        clientManager.recreateClient()
        return DomainResult.Success(Unit)
    }
}