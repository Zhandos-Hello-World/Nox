package io.bz.data.handlers

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import io.bz.data.lib.tdJson
import io.bz.domain.state.AuthState
import io.bz.domain.stores.AuthStore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("updateAuthorizationState")
data class UpdateAuthorizationState(
    @SerialName("authorization_state") val authorizationState: AuthState
)
class AuthUpdateHandler(
    private val authStore: AuthStore,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        val json = wrapper.jsonResponse ?: return false
        try {
            if (json.contains("updateAuthorizationState")) {
                val update = tdJson.decodeFromString<UpdateAuthorizationState>(json)
                authStore.updateState(update.authorizationState)
                return true
            }
        } catch (e: Exception) {
            println("Mapping error: ${e.message}")
        }
        return false
    }

    suspend fun readyState() {
        authStore.updateState(AuthState.Ready)
    }
}