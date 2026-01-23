package io.bz.data.mapper.auth

import org.drinkless.tdlib.TdApi
import io.bz.domain.state.AuthState

fun TdApi.EmailAddressResetState.toDomain() : AuthState.WaitEmailCode.EmailAddressResetState {
    return when (this) {
        is TdApi.EmailAddressResetStateAvailable -> {
            AuthState.WaitEmailCode.EmailAddressResetState.EmailAddressResetStateAvailable(
                waitPeriod = this.waitPeriod,
            )
        }
        is TdApi.EmailAddressResetStatePending -> {
            AuthState.WaitEmailCode.EmailAddressResetState.EmailAddressResetStatePending(
                resetIn = this.resetIn,
            )
        }
        else -> throw IllegalArgumentException("Unknown EmailAddressResetState: $this")
    }
}