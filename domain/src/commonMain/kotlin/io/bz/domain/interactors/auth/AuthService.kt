package io.bz.domain.interactors.auth

import io.bz.domain.core.DomainResult
import io.bz.domain.state.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthService {
    val state: StateFlow<AuthState>
    suspend fun sendIntent(authIntent: AuthIntent): DomainResult<Unit>
}