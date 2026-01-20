package io.bz.domain.interactors.auth

sealed interface AuthIntent {
    data class SendTDLibParameters(
        val useTestServer: Boolean = false,
        val databaseDirectory: String,
        val filesDirectory: String = "",
        val databaseEncryptionKey: ByteArray = ByteArray(32),
        val useFileDatabase: Boolean = true,
        val useChatInfoDatabase: Boolean = true,
        val useMessageDatabase: Boolean = true,
        val useSecretChats: Boolean = false,
        val systemLanguageCode: String = "en-GB",
        val deviceModel: String = "Pixel 7",
        val systemVersion: String = "16",
        val applicationVersion: String = "1.0.0",
    ) : AuthIntent
    data class SendPhone(val phoneNumber: String) : AuthIntent
    data class SendCode(val code: String) : AuthIntent
    data class SendPassword(val password: String) : AuthIntent
    data class SendEmail(val email: String) : AuthIntent
    data object SendLogout : AuthIntent
    data object Close : AuthIntent
    data object RecreateClient : AuthIntent
}