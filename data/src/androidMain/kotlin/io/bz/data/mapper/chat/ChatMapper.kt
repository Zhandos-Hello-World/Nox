package io.bz.data.mapper.chat

import org.drinkless.tdlib.TdApi
import io.bz.domain.model.chat.BusinessBotManageBar
import io.bz.domain.model.chat.ChatAvailableReactions
import io.bz.domain.model.chat.ChatJoinRequestsInfo
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.ChatNotificationSettings
import io.bz.domain.model.chat.ChatPhotoInfo
import io.bz.domain.model.chat.ChatPosition
import io.bz.domain.model.chat.ChatSource
import io.bz.domain.model.chat.EmojiStatus
import io.bz.domain.model.chat.Thumbnail
import io.bz.domain.model.chat.VideoChat
import io.bz.domain.model.chat.ChatActionBar
import io.bz.domain.model.chat.Background
import io.bz.domain.model.chat.ChatBackground
import io.bz.domain.model.Minithumbnail
import io.bz.domain.model.chat.ChatListType

class ChatMapper {

    fun map(from: TdApi.Chat?): ChatModel? {
        return from?.toDomain()
    }
}

fun TdApi.Chat.toDomain(): ChatModel = ChatModel(
    id = id,
    type = type.toDomain(),
    title = title,
    photo = photo?.toDomain(),
    accentColorId = accentColorId,
    backgroundCustomEmojiId = backgroundCustomEmojiId,
    upgradedGiftColors = upgradedGiftColors?.toDomain(),
    profileAccentColorId = profileAccentColorId,
    profileBackgroundCustomEmojiId = profileBackgroundCustomEmojiId,
    permissions = permissions.toDomain(),
    lastMessage = lastMessage?.toDomain(),
    positions = positions.map { it.toDomain() }.toList(),
    chatListTypes = chatLists.map { it.toDomain() }.toList(),
    messageSenderId = messageSenderId?.toDomain(),
    hasProtectedContent = hasProtectedContent,
    isTranslatable = isTranslatable,
    isMarkedAsUnread = isMarkedAsUnread,
    viewAsTopics = viewAsTopics,
    hasScheduledMessages = hasScheduledMessages,
    canBeDeletedOnlyForSelf = canBeDeletedOnlyForSelf,
    canBeDeletedForAllUsers = canBeDeletedForAllUsers,
    canBeReported = canBeReported,
    defaultDisableNotification = defaultDisableNotification,
    unreadCount = unreadCount,
    lastReadInboxMessageId = lastReadInboxMessageId,
    lastReadOutboxMessageId = lastReadOutboxMessageId,
    unreadMentionCount = unreadMentionCount,
    unreadReactionCount = unreadReactionCount,
    notificationSettings = notificationSettings.toDomain(),
    availableReactions = availableReactions.toDomain(),
    messageAutoDeleteTime = messageAutoDeleteTime,
    emojiStatus = emojiStatus?.toDomain(),
    replyMarkupMessageId = replyMarkupMessageId,
    clientData = clientData,
//    background = background?.toDomain(),
//    theme = theme?.toDomain(),
    businessBotManageBar = businessBotManageBar?.toDomain(),
    actionBar = actionBar?.toDomain(),
    videoChat = videoChat.toDomain(),
    pendingJoinRequests = pendingJoinRequests?.toDomain(),
)

fun TdApi.ChatPhotoInfo.toDomain(): ChatPhotoInfo = ChatPhotoInfo(
    small = small.toDomain(),
    big = big.toDomain(),
    minithumbnail = minithumbnail?.toDomain(),
    hasAnimation = hasAnimation,
    isPersonal = isPersonal
)

fun TdApi.Minithumbnail.toDomain(): Minithumbnail = Minithumbnail(
    width = width, height = height, data = data
)


fun TdApi.ChatList.toDomain(): ChatListType = when (this) {
    is TdApi.ChatListMain -> ChatListType.Main

    is TdApi.ChatListArchive -> ChatListType.Archive

    is TdApi.ChatListFolder -> ChatListType.Folder(chatFolderId)

    else -> error("Unknown ChatList: ${this::class.java}")
}


fun TdApi.ChatSource.toDomain(): ChatSource = when (this) {
    is TdApi.ChatSourceMtprotoProxy -> ChatSource.MtprotoProxy

    is TdApi.ChatSourcePublicServiceAnnouncement -> ChatSource.PublicServiceAnnouncement(
        type = type, text = text
    )

    else -> error("Unknown ChatSource: ${this::class.java}")
}

fun TdApi.ChatPosition.toDomain(): ChatPosition = ChatPosition(
    list = list.toDomain(), order = order, isPinned = isPinned, source = source?.toDomain()
)


fun TdApi.ChatNotificationSettings.toDomain(): ChatNotificationSettings = ChatNotificationSettings(
    useDefaultMuteFor = useDefaultMuteFor,
    muteFor = muteFor,
    useDefaultSound = useDefaultSound,
    soundId = soundId,
    useDefaultShowPreview = useDefaultShowPreview,
    showPreview = showPreview,
    useDefaultMuteStories = useDefaultMuteStories,
    muteStories = muteStories,
    useDefaultStorySound = useDefaultStorySound,
    storySoundId = storySoundId,
    useDefaultShowStoryPoster = useDefaultShowStoryPoster,
    showStoryPoster = showStoryPoster,
    useDefaultDisablePinnedMessageNotifications = useDefaultDisablePinnedMessageNotifications,
    disablePinnedMessageNotifications = disablePinnedMessageNotifications,
    useDefaultDisableMentionNotifications = useDefaultDisableMentionNotifications,
    disableMentionNotifications = disableMentionNotifications,
)

fun TdApi.ChatAvailableReactions.toDomain(): ChatAvailableReactions = when (this) {
    is TdApi.ChatAvailableReactionsAll -> ChatAvailableReactions.All(
        maxReactionCount = maxReactionCount
    )

    is TdApi.ChatAvailableReactionsSome -> ChatAvailableReactions.Some(
        reactions = reactions.map { it.toDomain() }, maxReactionCount = maxReactionCount
    )

    else -> error("Unknown ChatAvailableReactions: $this")
}

fun TdApi.EmojiStatusType.toDomain(): EmojiStatus.EmojiStatusType = when (this) {
    is TdApi.EmojiStatusTypeCustomEmoji -> EmojiStatus.EmojiStatusType.EmojiStatusTypeCustomEmoji(
        customEmojiId = customEmojiId
    )

    is TdApi.EmojiStatusTypeUpgradedGift -> EmojiStatus.EmojiStatusType.EmojiStatusTypeUpgradedGift(
        upgradedGiftId = upgradedGiftId,
        giftTitle = giftTitle,
        giftName = giftName,
        modelCustomEmojiId = modelCustomEmojiId,
        symbolCustomEmojiId = symbolCustomEmojiId,
        backdropColors = backdropColors.toDomain()
    )

    else -> error("Unknown EmojiStatusType: $this")
}

fun TdApi.EmojiStatus.toDomain(): EmojiStatus = EmojiStatus(
    type = type.toDomain(), expirationDate = expirationDate
)

fun TdApi.UpgradedGiftBackdropColors.toDomain(): EmojiStatus.EmojiStatusType.EmojiStatusTypeUpgradedGift.UpgradedGiftBackdropColors =
    EmojiStatus.EmojiStatusType.EmojiStatusTypeUpgradedGift.UpgradedGiftBackdropColors(
        centerColor = centerColor,
        edgeColor = edgeColor,
        symbolColor = symbolColor,
        textColor = textColor
    )


fun TdApi.ChatBackground.toDomain(): ChatBackground = ChatBackground(
    background = background.toDomain(), darkThemeDimming = darkThemeDimming
)

fun TdApi.Background.toDomain(): Background = Background(
    id = id,
    isDefault = isDefault,
    isDark = isDark,
    name = name,
    document = document?.toDomain(),
    type = type.toDomain()
)

fun TdApi.BackgroundType.toDomain(): Background.BackgroundType = when (this) {
    is TdApi.BackgroundTypeWallpaper -> Background.BackgroundType.Wallpaper(
        isBlurred = isBlurred, isMoving = isMoving
    )

    is TdApi.BackgroundTypePattern -> Background.BackgroundType.Pattern(
        isMoving = isMoving, fill = fill.toDomain(), intensity = intensity
    )

    is TdApi.BackgroundTypeFill -> Background.BackgroundType.Fill(
        fill = fill.toDomain()
    )

    is TdApi.BackgroundTypeChatTheme -> Background.BackgroundType.ChatTheme(
        themeName = themeName
    )

    else -> error("Unknown BackgroundType: ${this::class.java}")
}

fun TdApi.Document.toDomain(): Background.Document = Background.Document(
    fileName = fileName,
    mimeType = mimeType,
    minithumbnail = minithumbnail?.toDomain(),
    thumbnail = thumbnail?.toDomain(),
    file = document.toDomain()
)

fun TdApi.BackgroundFill.toDomain(): Background.BackgroundFill = when (this) {
    is TdApi.BackgroundFillSolid -> Background.BackgroundFill.Solid(color)
    is TdApi.BackgroundFillGradient -> Background.BackgroundFill.Gradient(
        topColor,
        bottomColor,
        rotationAngle
    )

    is TdApi.BackgroundFillFreeformGradient -> Background.BackgroundFill.FreeformGradient(colors.toList())
    else -> error("Unknown BackgroundFill: ${this::class.java}")
}

fun TdApi.Thumbnail.toDomain(): Thumbnail = Thumbnail(
    format = format.toDomain(), width = width, height = height, file = file.toDomain()
)

fun TdApi.ThumbnailFormat.toDomain(): Thumbnail.ThumbnailFormat = when (this) {
    is TdApi.ThumbnailFormatJpeg -> Thumbnail.ThumbnailFormat.Jpeg
    is TdApi.ThumbnailFormatGif -> Thumbnail.ThumbnailFormat.Gif
    is TdApi.ThumbnailFormatMpeg4 -> Thumbnail.ThumbnailFormat.Mpeg4
    is TdApi.ThumbnailFormatPng -> Thumbnail.ThumbnailFormat.Png
    is TdApi.ThumbnailFormatTgs -> Thumbnail.ThumbnailFormat.Tgs
    is TdApi.ThumbnailFormatWebm -> Thumbnail.ThumbnailFormat.Webm
    is TdApi.ThumbnailFormatWebp -> Thumbnail.ThumbnailFormat.Webp
    else -> error("Unknown ThumbnailFormat: ${this::class.java}")
}


fun TdApi.ChatActionBar.toDomain(): ChatActionBar = when (this) {
    is TdApi.ChatActionBarReportSpam -> ChatActionBar.ReportSpam

    is TdApi.ChatActionBarAddContact -> ChatActionBar.AddContact

    is TdApi.ChatActionBarSharePhoneNumber -> ChatActionBar.SharePhoneNumber

    is TdApi.ChatActionBarJoinRequest -> ChatActionBar.JoinRequest(
        title = title, isChannel = isChannel, requestDate = requestDate
    )

    is TdApi.ChatActionBarInviteMembers -> ChatActionBar.InviteMembers

    is TdApi.ChatActionBarReportAddBlock -> ChatActionBar.ReportAddBlock(
        canUnarchive = canUnarchive
    )

    else -> error("Unknown ChatActionBar: ${this::class.java}")
}

fun TdApi.BusinessBotManageBar.toDomain(): BusinessBotManageBar = BusinessBotManageBar(
    botUserId = botUserId, manageUrl = manageUrl, isBotPaused = isBotPaused
)

fun TdApi.VideoChat.toDomain(): VideoChat = VideoChat(
    groupCallId = groupCallId,
    hasParticipants = hasParticipants,
    defaultParticipantId = defaultParticipantId?.toDomain(),
)

fun TdApi.ChatJoinRequestsInfo.toDomain(): ChatJoinRequestsInfo = ChatJoinRequestsInfo(
    totalCount = totalCount, userIds = userIds.toList()
)
