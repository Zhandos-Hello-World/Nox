package io.bz.domain.model.chat

data class UnreadReaction(
    val type: MessageInteractionInfo.Reactions.MessageReaction.ReactionType,
    val senderId: Message.MessageSender,
    val isBig: Boolean
)
