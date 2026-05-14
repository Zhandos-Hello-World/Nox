package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UnreadReaction(
    @SerialName("type")
    val type: MessageInteractionInfo.MessageReaction.ReactionType,
    @SerialName("sender_id")
    val senderId: Message.MessageSender,
    @SerialName("is_big")
    val isBig: Boolean
)