package io.bz.domain.model.chat

sealed class ChatAvailableReactions {

    data class ChatAvailableReactionsAll(
        val maxReactionCount: Int
    ) : ChatAvailableReactions()

    data class ChatAvailableReactionsSome(
        val reactions: List<MessageInteractionInfo.Reactions.MessageReaction.ReactionType>,
        val maxReactionCount: Int
    ) : ChatAvailableReactions()
}
