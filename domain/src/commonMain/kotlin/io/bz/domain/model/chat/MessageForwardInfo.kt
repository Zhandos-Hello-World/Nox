package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
data class MessageForwardInfo(
    @SerialName("origin") val origin: MessageOrigin,
    @SerialName("date") val date: Int,
    @SerialName("source") val source: ForwardSource? = null,
    @SerialName("public_service_announcement_type") val publicServiceAnnouncementType: String? = null
) {

    @Serializable
    @JsonClassDiscriminator("@type")
    sealed interface MessageOrigin {

        @Serializable
        @SerialName("messageOriginUser")
        data class User(
            @SerialName("sender_user_id") val senderUserId: Long
        ) : MessageOrigin

        @Serializable
        @SerialName("messageOriginHiddenUser")
        data class HiddenUser(
            @SerialName("sender_name") val senderName: String
        ) : MessageOrigin

        @Serializable
        @SerialName("messageOriginChat")
        data class FromChat(
            @SerialName("sender_chat_id") val senderChatId: Long,
            @SerialName("author_signature") val authorSignature: String? = null
        ) : MessageOrigin

        @Serializable
        @SerialName("messageOriginChannel")
        data class Channel(
            @SerialName("chat_id") val chatId: Long,
            @SerialName("message_id") val messageId: Long,
            @SerialName("author_signature") val authorSignature: String? = null
        ) : MessageOrigin
    }
    @Serializable
    data class ForwardSource(
        @SerialName("chat_id") val chatId: Long? = null,
        @SerialName("message_id") val messageId: Long? = null,
        @SerialName("sender_id") val sender: Message.MessageSender? = null, // Твой sealed interface MessageSender
        @SerialName("sender_name") val senderName: String? = null,
        @SerialName("date") val date: Int? = null,
        @SerialName("is_outgoing") val isOutgoing: Boolean = false
    )
    }