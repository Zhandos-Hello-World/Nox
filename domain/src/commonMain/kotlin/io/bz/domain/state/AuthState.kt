package io.bz.domain.state

import io.bz.domain.model.FormattedText
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthState {

    data object None: AuthState

    /**
     * Initialization parameters are needed. Call setTdlibParameters to provide them.
     */
    @Serializable
    @SerialName("authorizationStateWaitTdlibParameters")
    data object WaitTDLibParameters: AuthState

    /**
     * TDLib needs the user's phone number to authorize. Call setAuthenticationPhoneNumber to provide the phone number, or use requestQrCodeAuthentication or checkAuthenticationBotToken for other authentication options.
     */
    @Serializable @SerialName("authorizationStateWaitPhoneNumber")
    data object WaitPhoneNumber: AuthState

    /**
     * The user needs to confirm authorization on another logged in device by scanning a QR code with the provided link.
     */
    data class WaitOtherDeviceConfirmation(
        val link: String,
    ): AuthState
    /**
     * TDLib needs the user's authentication code to authorize. Call checkAuthenticationCode to check the code.
     */
    @Serializable
    @SerialName("authorizationStateWaitCode")
    data class WaitCode(
        @SerialName("code_info")
        val codeInfo: AuthenticationCodeInfo,
    ): AuthState
    /**
     * The user is unregistered and need to accept terms of service and enter their first name and last name to finish registration. Call registerUser to accept the terms of service and provide the data.
     */
    data class WaitRegistration(
        val termsOfService: TermsOfService,
    ): AuthState {

        data class TermsOfService(
            val text: FormattedText,
            val minUserAge: Int,
            val showPopup: Boolean,
        )
    }
    /**
     * The user has been authorized, but needs to enter a 2-step verification password to start using the application. Call checkAuthenticationPassword to provide the password, or requestAuthenticationPasswordRecovery to recover the password, or deleteAccount to delete the account after a week.
     */
    @Serializable
    @SerialName("authorizationStateWaitPassword")
    data class WaitPassword(
        @SerialName("password_hint")
        val passwordHint: String,
        @SerialName("has_recovery_email_address")
        val hasRecoveryEmailAddress: Boolean,
        @SerialName("has_passport_data")
        val hasPassportData: Boolean,
        @SerialName("recovery_email_address_pattern")
        val recoveryEmailAddressPattern: String,
    ): AuthState
    /**
     * The user has been successfully authorized. TDLib is now ready to answer general requests.
     */
    @Serializable
    @SerialName("authorizationStateReady")
    data object Ready: AuthState
    /**
     * The user is currently logging out.
     */
    data object LoggingOut: AuthState
    /**
     * TDLib is closing, all subsequent queries will be answered with the error 500. Note that closing TDLib can take a while. All resources will be freed only after authorizationStateClosed has been received.
     */
    data object Closing: AuthState
    /**
     * TDLib client is in its final state. All databases are closed and all resources are released. No other updates will be received after this. All queries will be responded to with error code 500. To continue working, one must create a new instance of the TDLib client.
     */
    data object Closed: AuthState
    /**
     * TDLib needs the user's email address to authorize. Call setAuthenticationEmailAddress to provide the email address, or directly call checkAuthenticationEmailCode with Apple ID/Google ID token if allowed.
     */
    data class WaitEmailAddress(
        val allowAppleId: Boolean,
        val allowGoogleId: Boolean,
    ): AuthState
    /**
     * The user must buy Telegram Premium as an in-store purchase to log in. Call checkAuthenticationPremiumPurchase and then setAuthenticationPremiumPurchaseTransaction.
     */
    data class WaitPremiumPurchase(
        val storeProductId: String,
        val supportEmailAddress: String,
        val supportEmailSubject: String,
    ): AuthState
    /**
     * TDLib needs the user's authentication code sent to an email address to authorize. Call checkAuthenticationEmailCode to provide the code.
     */
    data class WaitEmailCode(
        val allowAppleId: Boolean,
        val allowGoogleId: Boolean,
        val codeInfo: EmailAddressAuthenticationCodeInfo,
        val emailAddressResetState: EmailAddressResetState?,
    ): AuthState {

        data class EmailAddressAuthenticationCodeInfo(
            val emailAddressPattern: String,
            val length: Int,
        )

        sealed interface EmailAddressResetState {
            data class EmailAddressResetStateAvailable(val waitPeriod: Int) : EmailAddressResetState
            data class EmailAddressResetStatePending(val resetIn: Int) : EmailAddressResetState
        }
    }
}

@Serializable
data class AuthenticationCodeInfo(
    @SerialName("phone_number")
    val phoneNumber: String,
    val type: AuthenticationCodeType,
    @SerialName("next_type")
    val nextType: AuthenticationCodeType? = null,
    val timeout: Int,
)
@Serializable
sealed interface AuthenticationCodeType {
    @Serializable
    @SerialName("authenticationCodeTypeTelegramMessage")
    data object TelegramMessage : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeSms")
    data object Sms : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeSmsWord")
    data object SmsWord : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeSmsPhrase")
    data object SmsPhrase : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeCall")
    data object Call : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeFlashCall")
    data object FlashCall : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeMissedCall")
    data object MissedCall : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeFragment")
    data object Fragment : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeFirebaseAndroid")
    data object FirebaseAndroid : AuthenticationCodeType

    @Serializable
    @SerialName("authenticationCodeTypeFirebaseIos")
    data object FirebaseIos : AuthenticationCodeType
}