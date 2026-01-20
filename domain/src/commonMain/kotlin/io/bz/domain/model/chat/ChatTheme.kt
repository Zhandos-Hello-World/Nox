package io.bz.domain.model.chat

sealed interface ChatTheme {

    data class Emoji(
        val name: String
    ) : ChatTheme

    data class Gift(
        val giftTheme: GiftChatTheme
    ) : ChatTheme
}
