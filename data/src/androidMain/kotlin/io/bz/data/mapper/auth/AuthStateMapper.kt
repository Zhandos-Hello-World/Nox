package io.bz.data.mapper.auth

import io.bz.data.lib.TdApi
import io.bz.domain.state.AuthState

class AuthStateMapper {

    fun map(authState: TdApi.AuthorizationState): AuthState {
        return when (authState) {
            is TdApi.AuthorizationStateWaitTdlibParameters -> AuthState.WaitTDLibParameters
            is TdApi.AuthorizationStateWaitPhoneNumber -> AuthState.WaitPhoneNumber
            is TdApi.AuthorizationStateReady -> AuthState.Ready
            is TdApi.AuthorizationStateLoggingOut -> AuthState.LoggingOut
            is TdApi.AuthorizationStateClosing -> AuthState.Closing
            is TdApi.AuthorizationStateClosed -> AuthState.Closed
            is TdApi.AuthorizationStateWaitOtherDeviceConfirmation -> AuthState.WaitOtherDeviceConfirmation(
                link = authState.link,
            )

            is TdApi.AuthorizationStateWaitCode -> AuthState.WaitCode(
                codeInfo = AuthState.WaitCode.AuthenticationCodeInfo(
                    phoneNumber = authState.codeInfo.phoneNumber,
                    type = authState.codeInfo.type.toDomain(),
                    nextType = authState.codeInfo.nextType?.toDomain(),
                    timeout = authState.codeInfo.timeout,
                )
            )

            is TdApi.AuthorizationStateWaitRegistration -> AuthState.WaitRegistration(
                termsOfService = authState.termsOfService.toDomain(),
            )

            is TdApi.AuthorizationStateWaitPassword -> AuthState.WaitPassword(
                passwordHint = authState.passwordHint,
                hasRecoveryEmailAddress = authState.hasRecoveryEmailAddress,
                hasPassportData = authState.hasPassportData,
                recoveryEmailAddressPattern = authState.recoveryEmailAddressPattern,
            )

            is TdApi.AuthorizationStateWaitEmailAddress -> AuthState.WaitEmailAddress(
                allowAppleId = authState.allowAppleId,
                allowGoogleId = authState.allowGoogleId,
            )

            is TdApi.AuthorizationStateWaitPremiumPurchase -> AuthState.WaitPremiumPurchase(
                storeProductId = authState.storeProductId,
                supportEmailAddress = authState.supportEmailAddress,
                supportEmailSubject = authState.supportEmailSubject,
            )

            is TdApi.AuthorizationStateWaitEmailCode -> AuthState.WaitEmailCode(
                allowAppleId = authState.allowAppleId,
                allowGoogleId = authState.allowGoogleId,
                codeInfo = authState.codeInfo.toDomain(),
                emailAddressResetState = authState.emailAddressResetState?.toDomain(),
            )

            else -> {
                throw IllegalArgumentException("Unknown authorization state: $authState")
            }
        }
    }
}
