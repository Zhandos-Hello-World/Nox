package io.bz.data.mapper.chat

import org.drinkless.tdlib.TdApi
import io.bz.data.mapper.auth.toDomain
import io.bz.domain.model.chat.InlineKeyboardButton
import io.bz.domain.model.chat.LinkPreview
import io.bz.domain.model.chat.LinkPreviewOptions
import io.bz.domain.model.chat.Message
import io.bz.domain.model.chat.MessageContent
import io.bz.domain.model.chat.MessageReplyTo
import io.bz.domain.model.chat.MessageSelfDestructType
import io.bz.domain.model.chat.MessageTopic
import io.bz.domain.model.chat.Messages
import io.bz.domain.model.chat.ReplyMarkup
import io.bz.domain.model.chat.RestrictionInfo
import io.bz.domain.model.chat.SuggestedPostInfo
import io.bz.domain.model.chat.UnreadReaction

fun TdApi.Messages.toDomain(): Messages = Messages(
    totalCount = totalCount,
    messages = messages.toList().map { it.toDomain() },
)

fun TdApi.Message.toDomain(): Message = Message(
    id = id,
    sender = senderId.toDomain(),
    chatId = chatId,

    sendingState = sendingState?.toDomain(),
    schedulingState = schedulingState?.toDomain(),

    isOutgoing = isOutgoing,
    isPinned = isPinned,
    isFromOffline = isFromOffline,
    canBeSaved = canBeSaved,
    hasTimestampedMedia = hasTimestampedMedia,
    isChannelPost = isChannelPost,
    isPaidStarSuggestedPost = isPaidStarSuggestedPost,
    isPaidTonSuggestedPost = isPaidTonSuggestedPost,
    containsUnreadMention = containsUnreadMention,

    date = date,
    editDate = editDate,

    forwardInfo = forwardInfo?.toDomain(),
    importInfo = importInfo?.toDomain(),
    interactionInfo = interactionInfo?.toDomain(),

    unreadReactions = unreadReactions.map { it.toDomain() },
    factCheck = factCheck?.toDomain(),
    suggestedPostInfo = suggestedPostInfo?.toDomain(),
    replyTo = replyTo?.toDomain(),
    topic = topicId?.toDomain(),

    selfDestructType = selfDestructType?.toDomain(),
    selfDestructInSeconds = selfDestructIn,
    autoDeleteInSeconds = autoDeleteIn,

    viaBotUserId = viaBotUserId,
    senderBusinessBotUserId = senderBusinessBotUserId,
    senderBoostCount = senderBoostCount,
    paidMessageStarCount = paidMessageStarCount,

    authorSignature = authorSignature,
    mediaAlbumId = mediaAlbumId,
    effectId = effectId,

    restrictionInfo = restrictionInfo?.toDomain(),
    content = content.toDomain(),
    replyMarkup = replyMarkup?.toDomain()
)


fun TdApi.MessageSender.toDomain(): Message.MessageSender = when (this) {
    is TdApi.MessageSenderUser -> Message.MessageSender.FromUser(userId = userId)

    is TdApi.MessageSenderChat -> Message.MessageSender.FromChat(chatId = chatId)

    else -> error("Unknown MessageSender: $this")
}


fun TdApi.UnreadReaction.toDomain(): UnreadReaction = UnreadReaction(
    type = type.toDomain(), senderId = senderId.toDomain(), isBig = isBig
)


fun TdApi.SuggestedPostPrice.toDomain(): SuggestedPostInfo.SuggestedPostPrice = when (this) {
    is TdApi.SuggestedPostPriceStar -> SuggestedPostInfo.SuggestedPostPrice.Stars(starCount)

    is TdApi.SuggestedPostPriceTon -> SuggestedPostInfo.SuggestedPostPrice.Ton(toncoinCentCount)

    else -> error("Unknown SuggestedPostPrice: $this")
}


fun TdApi.SuggestedPostState.toDomain(): SuggestedPostInfo.SuggestedPostState = when (this) {
    is TdApi.SuggestedPostStatePending -> SuggestedPostInfo.SuggestedPostState.PENDING
    is TdApi.SuggestedPostStateApproved -> SuggestedPostInfo.SuggestedPostState.APPROVED
    is TdApi.SuggestedPostStateDeclined -> SuggestedPostInfo.SuggestedPostState.DECLINED
    else -> error("Unknown SuggestedPostState: $this")
}


fun TdApi.SuggestedPostInfo.toDomain(): SuggestedPostInfo = SuggestedPostInfo(
    price = price?.toDomain(),
    sendDate = sendDate,
    state = state.toDomain(),
    canBeApproved = canBeApproved,
    canBeDeclined = canBeDeclined
)

fun TdApi.MessageReplyTo.toDomain(): MessageReplyTo = when (this) {
    is TdApi.MessageReplyToMessage -> MessageReplyTo.ReplyToMessage(
        chatId = chatId,
        messageId = messageId,
        quote = quote?.toDomain(),
        checklistTaskId = checklistTaskId,
        origin = origin?.toDomain(),
        originSendDate = originSendDate,
        content = content?.toDomain()
    )

    is TdApi.MessageReplyToStory -> MessageReplyTo.ReplyToStory(
        storyPosterChatId = storyPosterChatId, storyId = storyId
    )

    else -> error("Unknown MessageReplyTo: $this")
}

fun TdApi.TextQuote.toDomain(): MessageReplyTo.ReplyToMessage.TextQuote =
    MessageReplyTo.ReplyToMessage.TextQuote(
        text = text.toDomain(), position = position, isManual = isManual
    )

fun TdApi.MessageContent.toDomain(): MessageContent = when (this) {
    is TdApi.MessageText -> MessageContent.MessageText(
        text = text.toDomain(),
        linkPreview = linkPreview?.toDomain(),
        linkPreviewOptions = linkPreviewOptions?.toDomain()
    )
    is TdApi.MessageContactRegistered -> MessageContent.MessageContactRegistered

    else -> MessageContent.UnSupportedContent(this.javaClass.name)
}

fun TdApi.LinkPreview.toDomain(): LinkPreview = LinkPreview(
    url = url,
    displayUrl = displayUrl,
    siteName = siteName,
    title = title,
    description = description.toDomain(),
    author = author,
    type = type.toDomain(),
    hasLargeMedia = hasLargeMedia,
    showLargeMedia = showLargeMedia,
    showMediaAboveDescription = showMediaAboveDescription,
    skipConfirmation = skipConfirmation,
    showAboveText = showAboveText,
    instantViewVersion = instantViewVersion
)

fun TdApi.LinkPreviewType.toDomain(): LinkPreview.LinkPreviewType = when (this) {
    is TdApi.LinkPreviewTypeArticle -> LinkPreview.LinkPreviewType.LinkPreviewTypeArticle
    is TdApi.LinkPreviewTypePhoto -> LinkPreview.LinkPreviewType.LinkPreviewTypePhoto
    is TdApi.LinkPreviewTypeVideo -> LinkPreview.LinkPreviewType.LinkPreviewTypeVideo
    is TdApi.LinkPreviewTypeAnimation -> LinkPreview.LinkPreviewType.LinkPreviewTypeAnimation
    is TdApi.LinkPreviewTypeAudio -> LinkPreview.LinkPreviewType.LinkPreviewTypeAudio
    is TdApi.LinkPreviewTypeDocument -> LinkPreview.LinkPreviewType.LinkPreviewTypeDocument
    else -> LinkPreview.LinkPreviewType.LinkPreviewTypeUnknown
}

fun TdApi.LinkPreviewOptions.toDomain(): LinkPreviewOptions = LinkPreviewOptions(
    isDisabled = isDisabled,
    url = url,
    forceSmallMedia = forceSmallMedia,
    forceLargeMedia = forceLargeMedia,
    showAboveText = showAboveText
)

fun TdApi.MessageTopic.toDomain(): MessageTopic = when (this) {
    is TdApi.MessageTopicThread -> MessageTopic.Thread(
        messageThreadId = messageThreadId
    )

    is TdApi.MessageTopicForum -> MessageTopic.Forum(
        forumTopicId = forumTopicId
    )

    is TdApi.MessageTopicDirectMessages -> MessageTopic.DirectMessages(
        directMessagesChatTopicId = directMessagesChatTopicId
    )

    is TdApi.MessageTopicSavedMessages -> MessageTopic.SavedMessages(
        savedMessagesTopicId = savedMessagesTopicId
    )

    else -> error("Unknown MessageTopic: ${this::class.java}")
}

fun TdApi.MessageSelfDestructType.toDomain(): MessageSelfDestructType = when (this) {
    is TdApi.MessageSelfDestructTypeTimer -> MessageSelfDestructType.Timer(
        seconds = selfDestructTime
    )

    is TdApi.MessageSelfDestructTypeImmediately -> MessageSelfDestructType.Immediately

    else -> error("Unknown MessageSelfDestructType: ${this::class.java}")
}


fun TdApi.RestrictionInfo.toDomain(): RestrictionInfo = RestrictionInfo(
    reason = restrictionReason.takeIf { it.isNotBlank() }, hasSensitiveContent = hasSensitiveContent
)


fun TdApi.ReplyMarkup.toDomain(): ReplyMarkup = when (this) {
    is TdApi.ReplyMarkupRemoveKeyboard -> ReplyMarkup.RemoveKeyboard(
        isPersonal = isPersonal
    )

    is TdApi.ReplyMarkupForceReply -> ReplyMarkup.ForceReply(
        isPersonal = isPersonal,
        inputPlaceholder = inputFieldPlaceholder.takeIf { it.isNotBlank() })

    is TdApi.ReplyMarkupShowKeyboard -> ReplyMarkup.ShowKeyboard(
        rows = rows.map { row -> row.map { it.toDomain() } },
        isPersistent = isPersistent,
        resizeKeyboard = resizeKeyboard,
        oneTime = oneTime,
        isPersonal = isPersonal,
        inputPlaceholder = inputFieldPlaceholder.takeIf { it.isNotBlank() })

    is TdApi.ReplyMarkupInlineKeyboard -> ReplyMarkup.InlineKeyboard(
        rows = rows.map { row -> row.map { it.toDomain() } })

    else -> error("Unknown ReplyMarkup: ${this::class.java}")
}

fun TdApi.KeyboardButton.toDomain(): ReplyMarkup.ShowKeyboard.KeyboardButton =
    ReplyMarkup.ShowKeyboard.KeyboardButton(
        text = text, type = type.toDomain()
    )


fun TdApi.KeyboardButtonType.toDomain(): ReplyMarkup.ShowKeyboard.KeyboardButton.KeyboardButtonType =
    when (this) {
        is TdApi.KeyboardButtonTypeText -> ReplyMarkup.ShowKeyboard.KeyboardButton.KeyboardButtonType.Text

        is TdApi.KeyboardButtonTypeRequestPhoneNumber -> ReplyMarkup.ShowKeyboard.KeyboardButton.KeyboardButtonType.RequestPhoneNumber

        is TdApi.KeyboardButtonTypeRequestLocation -> ReplyMarkup.ShowKeyboard.KeyboardButton.KeyboardButtonType.RequestLocation

        is TdApi.KeyboardButtonTypeRequestPoll -> ReplyMarkup.ShowKeyboard.KeyboardButton.KeyboardButtonType.RequestPoll(
            forceRegular = forceRegular, forceQuiz = forceQuiz
        )

        is TdApi.KeyboardButtonTypeRequestUsers -> ReplyMarkup.ShowKeyboard.KeyboardButton.KeyboardButtonType.RequestUsers(
            userIsBot = userIsBot.takeIf { it },
            userIsPremium = userIsPremium.takeIf { it },
            maxQuantity = maxQuantity
        )

        is TdApi.KeyboardButtonTypeRequestChat -> ReplyMarkup.ShowKeyboard.KeyboardButton.KeyboardButtonType.RequestChat(
            isChannel = chatIsChannel,
            isForum = chatIsForum,
            hasUsername = chatHasUsername,
            isCreated = chatIsCreated,
//                userAdministratorRights = userAdministratorRights?.toDomain(),
//                botAdministratorRights = botAdministratorRights?.toDomain()
        )

        is TdApi.KeyboardButtonTypeWebApp -> ReplyMarkup.ShowKeyboard.KeyboardButton.KeyboardButtonType.WebApp(
            url = url
        )

        else -> error("Unknown KeyboardButtonType: ${this::class.java}")
    }


fun TdApi.InlineKeyboardButton.toDomain(): InlineKeyboardButton = InlineKeyboardButton(
    text = text, type = type.toDomain()
)

fun TdApi.InlineKeyboardButtonType.toDomain(): InlineKeyboardButton.InlineKeyboardButtonType =
    when (this) {
        is TdApi.InlineKeyboardButtonTypeUrl -> InlineKeyboardButton.InlineKeyboardButtonType.Url(
            url
        )

        is TdApi.InlineKeyboardButtonTypeLoginUrl -> InlineKeyboardButton.InlineKeyboardButtonType.LoginUrl(
            url = url,
            id = id.takeIf { it != 0L },
            forwardText = forwardText.takeIf { it.isNotBlank() })

        is TdApi.InlineKeyboardButtonTypeWebApp -> InlineKeyboardButton.InlineKeyboardButtonType.WebApp(
            url
        )

        is TdApi.InlineKeyboardButtonTypeCallback -> InlineKeyboardButton.InlineKeyboardButtonType.Callback(
            data
        )

        is TdApi.InlineKeyboardButtonTypeCallbackWithPassword -> InlineKeyboardButton.InlineKeyboardButtonType.CallbackWithPassword(
            data
        )

        is TdApi.InlineKeyboardButtonTypeCallbackGame -> InlineKeyboardButton.InlineKeyboardButtonType.CallbackGame

        is TdApi.InlineKeyboardButtonTypeSwitchInline -> InlineKeyboardButton.InlineKeyboardButtonType.SwitchInline(
            query = query, inCurrentChat = targetChat is TdApi.TargetChatCurrent
        )

        is TdApi.InlineKeyboardButtonTypeBuy -> InlineKeyboardButton.InlineKeyboardButtonType.Buy

        is TdApi.InlineKeyboardButtonTypeUser -> InlineKeyboardButton.InlineKeyboardButtonType.User(
            userId
        )

        is TdApi.InlineKeyboardButtonTypeCopyText -> InlineKeyboardButton.InlineKeyboardButtonType.CopyText(
            text
        )

        else -> error("Unknown InlineKeyboardButtonType: ${this::class.java}")
    }

