package io.bz.data.handlers

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import org.drinkless.tdlib.TdApi
import io.bz.data.mapper.auth.AuthStateMapper
import io.bz.domain.stores.AuthStore

class AuthUpdateHandler(
    private val authStore: AuthStore,
    private val mapper: AuthStateMapper,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        when (val state = wrapper.tdApi) {
            is TdApi.UpdateAuthorizationState -> {
                val authState = mapper.map(state.authorizationState)
                authStore.updateState(authState)
            }
            else -> return false
        }
        return true
    }


}