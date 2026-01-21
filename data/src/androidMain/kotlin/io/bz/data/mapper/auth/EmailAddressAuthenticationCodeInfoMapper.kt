package io.bz.data.mapper.auth

import io.bz.data.lib.TdApi
import io.bz.domain.state.AuthState

fun TdApi.EmailAddressAuthenticationCodeInfo.toDomain() : AuthState.WaitEmailCode.EmailAddressAuthenticationCodeInfo {
    return AuthState.WaitEmailCode.EmailAddressAuthenticationCodeInfo(
        emailAddressPattern = this.emailAddressPattern,
        length = this.length,
    )
}