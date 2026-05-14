package io.bz.domain.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.state.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val state: StateFlow<AuthState>
    suspend fun sendSetTdlibParameters(intent: AuthIntent.SendTDLibParameters): DomainResult<Unit>
    suspend fun sendPhone(phoneNumber: String): DomainResult<Unit>
    suspend fun sendsSMSCode(code: String): DomainResult<Unit>

    suspend fun resendSmsCode(): DomainResult<Unit>
    suspend fun sendPassword(password: String): DomainResult<Unit>
    suspend fun sendEmail(email: String): DomainResult<Unit>
    suspend fun sendLogout(): DomainResult<Unit>
    suspend fun sendClose(): DomainResult<Unit>
    suspend fun recreate(): DomainResult<Unit>
}