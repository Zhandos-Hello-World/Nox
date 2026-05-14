package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("@type")
sealed class ChatAvailableReactions {

    @Serializable
    @SerialName("chatAvailableReactionsAll")
    data class All(
        @SerialName("max_reaction_count")
        val maxReactionCount: Int
    ) : ChatAvailableReactions()

    @Serializable
    @SerialName("chatAvailableReactionsSome")
    data class Some(
        @SerialName("reactions")
        val reactions: List<MessageInteractionInfo.MessageReaction.ReactionType>,

        @SerialName("max_reaction_count")
        val maxReactionCount: Int
    ) : ChatAvailableReactions()
}