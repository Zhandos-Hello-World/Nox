package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
data class MessageInteractionInfo(
    @SerialName("view_count") val viewCount: Int,
    @SerialName("forward_count") val forwardCount: Int,
    @SerialName("reply_info") val replyInfo: MessageReplyInfo? = null,
    @SerialName("reactions") val reactions: Reactions? = null
) {
    @Serializable
    data class MessageReplyInfo(
        @SerialName("reply_count") val replyCount: Int,
        @SerialName("recent_replier_ids") val recentReplierIds: List<Message.MessageSender> = emptyList(),
        @SerialName("last_read_inbox_message_id") val lastReadInboxMessageId: Long,
        @SerialName("last_read_outbox_message_id") val lastReadOutboxMessageId: Long,
        @SerialName("last_message_id") val lastMessageId: Long
    )

    @Serializable
    data class Reactions(
        @SerialName("reactions") val reactions: List<MessageReaction> = emptyList(),
        @SerialName("are_tags") val areTags: Boolean,
        @SerialName("paid_reactors") val paidReactors: List<PaidReactor> = emptyList(),
        @SerialName("can_get_added_reactions") val canGetAddedReactions: Boolean
    ) {
        @Serializable
        data class PaidReactor(
            @SerialName("sender_id") val senderId: Message.MessageSender? = null,
            @SerialName("star_count") val starCount: Long,
            @SerialName("is_top") val isTop: Boolean,
            @SerialName("is_me") val isMe: Boolean,
            @SerialName("is_anonymous") val isAnonymous: Boolean
        )
    }


    @Serializable
    data class MessageReaction(
        @SerialName("type") val type: ReactionType,
        @SerialName("total_count") val totalCount: Int,
        @SerialName("is_chosen") val isChosen: Boolean,
        @SerialName("used_sender_id") val usedSenderId: Message.MessageSender? = null,
        @SerialName("recent_sender_ids") val recentSenderIds: List<Message.MessageSender> = emptyList()
    ) {
        @Serializable
        @JsonClassDiscriminator("@type")
        sealed interface ReactionType {

            @Serializable
            @SerialName("reactionTypeEmoji")
            data class Emoji(@SerialName("emoji") val emoji: String) : ReactionType

            @Serializable
            @SerialName("reactionTypeCustomEmoji")
            data class CustomEmoji(@SerialName("custom_emoji_id") val customEmojiId: Long) : ReactionType

            @Serializable
            @SerialName("reactionTypePaid")
            data object Paid : ReactionType
        }
    }

}