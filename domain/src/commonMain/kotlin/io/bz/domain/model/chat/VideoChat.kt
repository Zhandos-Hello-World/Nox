package io.bz.domain.model.chat

data class VideoChat(
    val groupCallId: Int,
    val hasParticipants: Boolean,
    val defaultParticipantId: Message.MessageSender?,
)
