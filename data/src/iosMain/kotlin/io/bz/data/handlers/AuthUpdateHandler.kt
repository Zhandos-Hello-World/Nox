package io.bz.data.handlers

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import io.bz.domain.state.AuthState
import io.bz.domain.stores.AuthStore

class AuthUpdateHandler(
    private val authStore: AuthStore,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        val response = wrapper.jsonResponse
        when {
            response.contains("updateAuthorizationState") -> {
                when {
                    response.contains("authorizationStateWaitTdlibParameters") -> {
                        authStore.updateState(AuthState.WaitTDLibParameters)
                    }
                    response.contains("authorizationStateWaitPhoneNumber") -> {
                        authStore.updateState(AuthState.WaitPhoneNumber)
                    }
                }
            }
            else -> return false
        }

        return true
    }

    suspend fun readyState() {
        authStore.updateState(AuthState.Ready)
    }
}