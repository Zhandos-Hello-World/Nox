package io.bz.data.interactors

import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.auth.AuthIntent
import io.bz.domain.interactors.auth.AuthService
import io.bz.domain.repository.AuthRepository
import io.bz.domain.state.AuthState
import kotlinx.coroutines.flow.StateFlow

class AuthServiceImpl(
    private val repository: AuthRepository,
) : AuthService {
    override val state: StateFlow<AuthState> = repository.state

    override suspend fun sendIntent(authIntent: AuthIntent): DomainResult<Unit> {
        return with(repository) {
            when (authIntent) {
                is AuthIntent.SendTDLibParameters -> sendSetTdlibParameters(authIntent)
                is AuthIntent.SendCode -> sendsSMSCode(authIntent.code)
                is AuthIntent.SendEmail -> sendEmail(authIntent.email)
                is AuthIntent.SendPassword -> sendPassword(authIntent.password)
                is AuthIntent.SendPhone -> sendPhone(authIntent.phoneNumber)
                is AuthIntent.SendLogout -> sendLogout()
                is AuthIntent.Close -> sendClose()
                is AuthIntent.RecreateClient -> recreate()
                is AuthIntent.ResendCode -> resendSmsCode()
            }
        }
    }
}