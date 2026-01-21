package io.bz.data.mapper.chat

import io.bz.data.lib.TdApi
import io.bz.domain.model.chat.MessageInteractionInfo

fun TdApi.MessageInteractionInfo.toDomain(): MessageInteractionInfo =
    MessageInteractionInfo(
        viewCount = viewCount,
        forwardCount = forwardCount,
        replyInfo = replyInfo?.toDomain(),
        reactions = reactions?.toDomain()
    )

fun TdApi.MessageReplyInfo.toDomain(): MessageInteractionInfo.MessageReplyInfo =
    MessageInteractionInfo.MessageReplyInfo(
        replyCount = replyCount,
        recentReplierIds = recentReplierIds.map { it.toDomain() },
        lastReadInboxMessageId = lastReadInboxMessageId,
        lastReadOutboxMessageId = lastReadOutboxMessageId,
        lastMessageId = lastMessageId
    )


fun TdApi.MessageReactions.toDomain(): MessageInteractionInfo.Reactions =
    MessageInteractionInfo.Reactions(
        reactions = reactions.map { it.toDomain() },
        areTags = areTags,
        paidReactors = paidReactors.map { it.toDomain() },
        canGetAddedReactions = canGetAddedReactions
    )

fun TdApi.MessageReaction.toDomain(): MessageInteractionInfo.Reactions.MessageReaction =
    MessageInteractionInfo.Reactions.MessageReaction(
        type = type.toDomain(),
        totalCount = totalCount,
        isChosen = isChosen,
        usedSenderId = usedSenderId?.toDomain(),
        recentSenderIds = recentSenderIds.map { it.toDomain() }
    )

fun TdApi.ReactionType.toDomain(): MessageInteractionInfo.Reactions.MessageReaction.ReactionType =
    when (this) {
        is TdApi.ReactionTypeEmoji ->
            MessageInteractionInfo.Reactions.MessageReaction.ReactionType.Emoji(emoji)

        is TdApi.ReactionTypeCustomEmoji ->
            MessageInteractionInfo.Reactions.MessageReaction.ReactionType.CustomEmoji(customEmojiId)

        is TdApi.ReactionTypePaid ->
            MessageInteractionInfo.Reactions.MessageReaction.ReactionType.Paid

        else -> error("Unknown ReactionType: $this")
    }


fun TdApi.PaidReactor.toDomain(): MessageInteractionInfo.Reactions.PaidReactor =
    MessageInteractionInfo.Reactions.PaidReactor(
        senderId = senderId?.toDomain(),
        starCount = starCount,
        isTop = isTop,
        isMe = isMe,
        isAnonymous = isAnonymous
    )
