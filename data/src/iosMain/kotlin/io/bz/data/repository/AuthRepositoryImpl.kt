package io.bz.data.repository

import io.bz.data.TdLibCheck
import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.repository.AuthRepository
import io.bz.domain.state.AuthState
import io.bz.domain.stores.AuthStore
import kotlinx.coroutines.flow.StateFlow

class AuthRepositoryImpl(
    store: AuthStore,
): AuthRepository {
    override val state: StateFlow<AuthState> = store.state

    init {
        val tdlib = TdLibCheck()
        tdlib.runTest()
    }

    override suspend fun sendSetTdlibParameters(intent: AuthIntent.SendTDLibParameters): DomainResult<Unit> {
        val appId = 0
        val appHash = "BuildConfig.API_HASH"
        return DomainResult.Success(Unit)
    }

    override suspend fun sendPhone(phoneNumber: String): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }

    override suspend fun sendsSMSCode(code: String): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }

    override suspend fun sendPassword(password: String): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }

    override suspend fun sendEmail(email: String): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }

    override suspend fun sendLogout(): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }

    override suspend fun sendClose(): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }

    override suspend fun recreate(): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }
}