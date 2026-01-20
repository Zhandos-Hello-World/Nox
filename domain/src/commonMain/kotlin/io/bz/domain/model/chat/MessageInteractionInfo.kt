package io.bz.domain.model.chat

data class MessageInteractionInfo(
    val viewCount: Int,
    val forwardCount: Int,
    val replyInfo: MessageReplyInfo?,
    val reactions: Reactions?
) {
    data class MessageReplyInfo(
        val replyCount: Int,
        val recentReplierIds: List<Message.MessageSender>,
        val lastReadInboxMessageId: Long,
        val lastReadOutboxMessageId: Long,
        val lastMessageId: Long
    )

    data class Reactions(
        val reactions: List<MessageReaction>,
        val areTags: Boolean,
        val paidReactors: List<PaidReactor>,
        val canGetAddedReactions: Boolean
    ) {

        data class PaidReactor(
            val senderId: Message.MessageSender?,
            val starCount: Long,
            val isTop: Boolean,
            val isMe: Boolean,
            val isAnonymous: Boolean
        )


        data class MessageReaction(
            val type: ReactionType,
            val totalCount: Int,
            val isChosen: Boolean,
            val usedSenderId: Message.MessageSender?,
            val recentSenderIds: List<Message.MessageSender>
        ) {
            sealed interface ReactionType {

                data class Emoji(
                    val emoji: String
                ) : ReactionType

                data class CustomEmoji(
                    val customEmojiId: Long
                ) : ReactionType

                object Paid : ReactionType
            }

        }
    }

}