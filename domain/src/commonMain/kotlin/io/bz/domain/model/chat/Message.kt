package io.bz.domain.model.chat

import io.bz.domain.model.Error
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@SerialName("message")
data class Message(
//    @SerialName("is_pinned") val isPinned: Boolean,
//    @SerialName("is_paid_star_suggested_post") val isPaidStarSuggestedPost: Boolean,
//    @SerialName("is_paid_ton_suggested_post") val isPaidTonSuggestedPost: Boolean,
//    @SerialName("is_from_offline") val isFromOffline: Boolean,
//    @SerialName("can_be_saved") val canBeSaved: Boolean,
//    @SerialName("has_timestamped_media") val hasTimestampedMedia: Boolean,
//    @SerialName("is_channel_post") val isChannelPost: Boolean,
//    @SerialName("contains_unread_mention") val containsUnreadMention: Boolean,
//    @SerialName("edit_date") val editDate: Int,
//    @SerialName("import_info") val importInfo: MessageImportInfo? = null,
//    @SerialName("unread_reactions") val unreadReactions: List<UnreadReaction> = emptyList(),
//    @SerialName("self_destruct_in_seconds") val selfDestructInSeconds: Double = 0.0,
//    @SerialName("auto_delete_in_seconds") val autoDeleteInSeconds: Double = 0.0,
//    @SerialName("via_bot_user_id") val viaBotUserId: Long = 0,
//    @SerialName("author_signature") val authorSignature: String? = null,
//    @SerialName("media_album_id") val mediaAlbumId: Long = 0,
//    @SerialName("reply_markup") val replyMarkup: ReplyMarkup? = null,
//    @SerialName("fact_check") val factCheck: FactCheck? = null,
//    @SerialName("topic") val topic: MessageTopic? = null,
//    @SerialName("self_destruct_type") val selfDestructType: MessageSelfDestructType? = null,
//    @SerialName("sender_business_bot_user_id") val senderBusinessBotUserId: Long = 0,
//    @SerialName("sender_boost_count") val senderBoostCount: Int = 0,
//    @SerialName("paid_message_star_count") val paidMessageStarCount: Long = 0,
//    @SerialName("effect_id") val effectId: Long = 0,
//    @SerialName("restriction_info") val restrictionInfo: RestrictionInfo? = null,
//    @SerialName("suggested_post_info") val suggestedPostInfo: SuggestedPostInfo? = null,

    @SerialName("id") val id: Long,
//    @SerialName("sender_id") val senderId: MessageSender,
//    @SerialName("chat_id") val chatId: Long,
//    @SerialName("content") val content: MessageContent,

    // Все эти поля ОБЯЗАТЕЛЬНО должны быть nullable в TDLib
//    @SerialName("interaction_info") val interactionInfo: MessageInteractionInfo? = null,
//    @SerialName("sending_state") val sendingState: JsonElement? = null, // Можно временно юзать JsonElement
//    @SerialName("scheduling_state") val schedulingState: JsonElement? = null,
//    @SerialName("reply_to") val replyTo: JsonElement? = null,
//    @SerialName("forward_info") val forwardInfo: JsonElement? = null

    // СДЕЛАЙТЕ ЭТИ ПОЛЯ ТАКИМИ:
//    @SerialName("sending_state") val sendingState: MessageSendingState? = null,
//    @SerialName("scheduling_state") val schedulingState: MessageSchedulingState? = null,
//    @SerialName("forward_info") val forwardInfo: MessageForwardInfo? = null,
//    @SerialName("reply_to") val replyTo: MessageReplyTo? = null,
//    @SerialName("interaction_info") val interactionInfo: MessageInteractionInfo? = null,
//
//    // Примитивы тоже лучше обезопасить значениями по умолчанию
    @SerialName("is_outgoing") val isOutgoing: Boolean = false,
    @SerialName("date") val date: Int = 0,

    @SerialName("content") val content: MessageContent = MessageContent.UnSupportedContent()
) {

    @Serializable
    @JsonClassDiscriminator("@type")
    sealed interface MessageSender {
        @Serializable
        @SerialName("messageSenderUser")
        data class FromUser(@SerialName("user_id") val userId: Long) : MessageSender

        @Serializable
        @SerialName("messageSenderChat")
        data class FromChat(@SerialName("chat_id") val chatId: Long) : MessageSender
    }


    @Serializable
    @JsonClassDiscriminator("@type")
    sealed interface MessageSendingState {
        @Serializable
        @SerialName("messageSendingStatePending")
        data class Pending(
            @SerialName("sending_id") val sendingId: Int,
        ) : MessageSendingState

        @Serializable
        @SerialName("messageSendingStateFailed")
        data class Failed(
            @SerialName("error")
            val error: Error, // Твоя модель ошибки (code, message)

            @SerialName("can_retry")
            val canRetry: Boolean,

            @SerialName("need_another_sender")
            val needAnotherSender: Boolean,

            @SerialName("need_another_reply_quote")
            val needAnotherReplyQuote: Boolean,

            @SerialName("need_drop_reply")
            val needDropReply: Boolean,

            @SerialName("required_paid_message_star_count")
            val requiredPaidMessageStarCount: Long,

            @SerialName("retry_after")
            val retryAfterSeconds: Double
        ) : MessageSendingState
    }


    @Serializable
    @JsonClassDiscriminator("@type")
    sealed interface MessageSchedulingState {

        @Serializable
        @SerialName("messageSchedulingStateSendAtDate")
        data class SendAtDate(
            @SerialName("send_date") val sendDate: Int,
            @SerialName("repeat_period") val repeatPeriodSeconds: Int = 0
        ) : MessageSchedulingState

        @Serializable
        @SerialName("messageSchedulingStateSendWhenOnline")
        data class SendWhenOnline(
            @SerialName("send_date") val sendDate: Int = 0
        ) : MessageSchedulingState

        @Serializable
        @SerialName("messageSchedulingStateSendWhenVideoProcessed")
        data class SendWhenVideoProcessed(
            @SerialName("expected_send_date") val expectedSendDate: Int
        ) : MessageSchedulingState
    }


    @Serializable
    data class MessageImportInfo(
        @SerialName("sender_name") val senderName: String,
        @SerialName("date") val date: Int,
    )

}