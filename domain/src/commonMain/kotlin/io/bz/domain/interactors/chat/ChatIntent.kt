package io.bz.domain.interactors.chat

import io.bz.domain.model.chat.ChatListType
import io.bz.domain.model.chat.MessageTopic

sealed interface ChatIntent {
    data class LoadChats(
        val type: ChatListType,
        val limit: Int,
    ): ChatIntent

    data class LoadChatHistory(
        val chatId: Long,
        val fromMessageId: Long,
        val offset: Int,
        val limit: Int,
        val onlyLocal: Boolean,
    ) : ChatIntent

    data class LoadDirectMessagesChatTopics(
        val chatID: Int,
        val limit: Int,
    ): ChatIntent

//    data class SendMessage(
//        val chatID: Int,
//        val topicID: MessageTopic,
//        val replyTo: InputMessageReplyTo,
//
//    )

}