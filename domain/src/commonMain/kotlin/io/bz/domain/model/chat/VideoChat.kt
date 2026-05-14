package io.bz.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoChat(
    @SerialName("group_call_id")
    val groupCallId: Int,
    @SerialName("has_participants")
    val hasParticipants: Boolean,
    @SerialName("default_participant_id")
    val defaultParticipantId: Message.MessageSender? = null
)