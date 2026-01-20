package io.bz.domain.model.chat

data class InlineKeyboardButton(
    val text: String,
    val type: InlineKeyboardButtonType
) {

    sealed interface InlineKeyboardButtonType {

        data class Url(
            val url: String
        ) : InlineKeyboardButtonType

        data class LoginUrl(
            val url: String,
            val id: Long?,
            val forwardText: String?
        ) : InlineKeyboardButtonType

        data class WebApp(
            val url: String
        ) : InlineKeyboardButtonType

        data class Callback(
            val data: ByteArray
        ) : InlineKeyboardButtonType

        data class CallbackWithPassword(
            val data: ByteArray
        ) : InlineKeyboardButtonType

        object CallbackGame : InlineKeyboardButtonType

        data class SwitchInline(
            val query: String,
            val inCurrentChat: Boolean
        ) : InlineKeyboardButtonType

        object Buy : InlineKeyboardButtonType

        data class User(
            val userId: Long
        ) : InlineKeyboardButtonType

        data class CopyText(
            val text: String
        ) : InlineKeyboardButtonType
    }

}
