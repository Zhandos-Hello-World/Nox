package io.bz.domain.stores

import io.bz.domain.state.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthStore {
    val state: StateFlow<AuthState>

    suspend fun updateState(state: AuthState)
}