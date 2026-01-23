package io.bz.data.stores

import io.bz.domain.state.AuthState
import io.bz.domain.stores.AuthStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthStoreImpl() : AuthStore {
    private val _state = MutableStateFlow<AuthState>(AuthState.None)
    override val state: StateFlow<AuthState> =  _state.asStateFlow()

    override suspend fun updateState(state: AuthState) {
        _state.value = state
    }
}