package io.bz.domain.model.chat

import io.bz.domain.model.Error

data class Message(
    val id: Long,
    val sender: MessageSender,
    val chatId: Long,

    val sendingState: MessageSendingState?,
    val schedulingState: MessageSchedulingState?,

    val isOutgoing: Boolean,
    val isPinned: Boolean,
    val isFromOffline: Boolean,
    val canBeSaved: Boolean,
    val hasTimestampedMedia: Boolean,
    val isChannelPost: Boolean,
    val isPaidStarSuggestedPost: Boolean,
    val isPaidTonSuggestedPost: Boolean,
    val containsUnreadMention: Boolean,

    val date: Int,
    val editDate: Int,

    val forwardInfo: MessageForwardInfo?,
    val importInfo: MessageImportInfo?,
    val interactionInfo: MessageInteractionInfo?,

    val unreadReactions: List<UnreadReaction>,
    val factCheck: FactCheck?,
    val suggestedPostInfo: SuggestedPostInfo?,
    val replyTo: MessageReplyTo?,
    val topic: MessageTopic?,

    val selfDestructType: MessageSelfDestructType?,
    val selfDestructInSeconds: Double,
    val autoDeleteInSeconds: Double,

    val viaBotUserId: Long,
    val senderBusinessBotUserId: Long,
    val senderBoostCount: Int,
    val paidMessageStarCount: Long,

    val authorSignature: String?,
    val mediaAlbumId: Long,
    val effectId: Long,

    val restrictionInfo: RestrictionInfo?,
    val content: MessageContent,
    val replyMarkup: ReplyMarkup?
) {

    sealed interface MessageSender {
        data class FromUser(val userId: Long) : MessageSender
        data class FromChat(val chatId: Long) : MessageSender
    }


    sealed interface MessageSendingState {

        data class Pending(
            val sendingId: Int
        ) : MessageSendingState

        data class Failed(
            val error: Error,
            val canRetry: Boolean,
            val needAnotherSender: Boolean,
            val needAnotherReplyQuote: Boolean,
            val needDropReply: Boolean,
            val requiredPaidMessageStarCount: Long,
            val retryAfterSeconds: Double
        ) : MessageSendingState

    }


    sealed interface MessageSchedulingState {
        data class SendAtDate(
            val sendDate: Int,
            val repeatPeriodSeconds: Int
        ) : MessageSchedulingState

        data object SendWhenOnline : MessageSchedulingState

        data class SendWhenVideoProcessed(
            val expectedSendDate: Int
        ) : MessageSchedulingState
    }


    data class MessageImportInfo(
        val senderName: String,
        val date: Int
    )

}