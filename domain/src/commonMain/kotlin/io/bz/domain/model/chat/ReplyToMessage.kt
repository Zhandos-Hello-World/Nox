package io.bz.domain.model.chat

import io.bz.domain.model.FormattedText

sealed interface MessageReplyTo {
    data class ReplyToMessage(
        val chatId: Long,                // 0 if unknown
        val messageId: Long,             // 0 if unknown
        val quote: TextQuote?,           // null if none
        val checklistTaskId: Int,        // 0 if none
        val origin: MessageForwardInfo.MessageOrigin?,      // null if same chat
        val originSendDate: Int,         // unix seconds, 0 if same chat
        val content: MessageContent?     // null if same chat or no media
    ) : MessageReplyTo {

        data class TextQuote(
            val text: FormattedText,
            val position: Int,   // UTF-16 code units
            val isManual: Boolean
        )

    }

    data class ReplyToStory(
        val storyPosterChatId: Long,
        val storyId: Int
    ) : MessageReplyTo
}

