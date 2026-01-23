package io.bz.data.mapper.chat

import org.drinkless.tdlib.TdApi
import io.bz.data.mapper.auth.toDomain
import io.bz.domain.model.Error
import io.bz.domain.model.chat.ChatPermission
import io.bz.domain.model.chat.ChatPermissions
import io.bz.domain.model.chat.ChatType
import io.bz.domain.model.chat.Message
import io.bz.domain.model.chat.UpgradedGiftColors
import io.bz.domain.model.chat.FactCheck
import io.bz.domain.model.chat.MessageForwardInfo

fun TdApi.ChatType.toDomain(): ChatType = when (this) {
    is TdApi.ChatTypePrivate -> ChatType.PRIVATE
    is TdApi.ChatTypeBasicGroup -> ChatType.BASIC_GROUP
    is TdApi.ChatTypeSupergroup -> ChatType.SUPERGROUP
    is TdApi.ChatTypeSecret -> ChatType.SECRET
    else -> throw IllegalArgumentException("Unknown type")
}


fun TdApi.UpgradedGiftColors.toDomain(): UpgradedGiftColors =
    UpgradedGiftColors(
        id = id,
        modelEmojiId = modelCustomEmojiId,
        symbolEmojiId = symbolCustomEmojiId,
        lightTheme = UpgradedGiftColors.GiftThemeColors(
            accentColor = lightThemeAccentColor,
            colors = lightThemeColors.toList()
        ),
        darkTheme = UpgradedGiftColors.GiftThemeColors(
            accentColor = darkThemeAccentColor,
            colors = darkThemeColors.toList()
        )
    )


fun TdApi.ChatPermissions.toDomain(): ChatPermissions {
    val perms = mutableSetOf<ChatPermission>()

    if (canSendBasicMessages) perms += ChatPermission.SEND_BASIC_MESSAGES
    if (canSendAudios) perms += ChatPermission.SEND_AUDIOS
    if (canSendDocuments) perms += ChatPermission.SEND_DOCUMENTS
    if (canSendPhotos) perms += ChatPermission.SEND_PHOTOS
    if (canSendVideos) perms += ChatPermission.SEND_VIDEOS
    if (canSendVideoNotes) perms += ChatPermission.SEND_VIDEO_NOTES
    if (canSendVoiceNotes) perms += ChatPermission.SEND_VOICE_NOTES
    if (canSendPolls) perms += ChatPermission.SEND_POLLS
    if (canSendOtherMessages) perms += ChatPermission.SEND_OTHER_MESSAGES
    if (canAddLinkPreviews) perms += ChatPermission.ADD_LINK_PREVIEWS
    if (canChangeInfo) perms += ChatPermission.CHANGE_INFO
    if (canInviteUsers) perms += ChatPermission.INVITE_USERS
    if (canPinMessages) perms += ChatPermission.PIN_MESSAGES
    if (canCreateTopics) perms += ChatPermission.CREATE_TOPICS

    return ChatPermissions(perms)
}

fun TdApi.MessageSendingState.toDomain(): Message.MessageSendingState =
    when (this) {
        is TdApi.MessageSendingStatePending ->
            Message.MessageSendingState.Pending(
                sendingId = sendingId
            )

        is TdApi.MessageSendingStateFailed ->
            Message.MessageSendingState.Failed(
                error = Error(
                    code = error.code,
                    message = error.message
                ),
                canRetry = canRetry,
                needAnotherSender = needAnotherSender,
                needAnotherReplyQuote = needAnotherReplyQuote,
                needDropReply = needDropReply,
                requiredPaidMessageStarCount = requiredPaidMessageStarCount,
                retryAfterSeconds = retryAfter
            )

        else -> error("Unknown MessageSendingState: $this")
    }

fun TdApi.MessageSchedulingState.toDomain(): Message.MessageSchedulingState =
    when (this) {
        is TdApi.MessageSchedulingStateSendAtDate ->
            Message.MessageSchedulingState.SendAtDate(
                sendDate = sendDate,
                repeatPeriodSeconds = repeatPeriod
            )

        is TdApi.MessageSchedulingStateSendWhenOnline ->
            Message.MessageSchedulingState.SendWhenOnline

        is TdApi.MessageSchedulingStateSendWhenVideoProcessed ->
            Message.MessageSchedulingState.SendWhenVideoProcessed(
                expectedSendDate = sendDate
            )

        else -> error("Unknown MessageSchedulingState: $this")
    }

fun TdApi.MessageForwardInfo.toDomain(): MessageForwardInfo =
    MessageForwardInfo(
        origin = origin.toDomain(),
        date = date,
        source = source?.toDomain(),
        publicServiceAnnouncementType =
            publicServiceAnnouncementType.takeIf { it.isNotEmpty() }
    )

fun TdApi.MessageOrigin.toDomain(): MessageForwardInfo.MessageOrigin =
    when (this) {
        is TdApi.MessageOriginUser ->
            MessageForwardInfo.MessageOrigin.User(
                senderUserId = senderUserId
            )

        is TdApi.MessageOriginHiddenUser ->
            MessageForwardInfo.MessageOrigin.HiddenUser(
                senderName = senderName
            )

        is TdApi.MessageOriginChat ->
            MessageForwardInfo.MessageOrigin.FromChat(
                senderChatId = senderChatId,
                authorSignature = authorSignature.takeIf { it.isNotEmpty() }
            )

        is TdApi.MessageOriginChannel ->
            MessageForwardInfo.MessageOrigin.Channel(
                chatId = chatId,
                messageId = messageId,
                authorSignature = authorSignature.takeIf { it.isNotEmpty() }
            )

        else -> error("Unknown MessageOrigin: $this")
    }


fun TdApi.ForwardSource.toDomain(): MessageForwardInfo.ForwardSource =
    MessageForwardInfo.ForwardSource(
        chatId = chatId.takeIf { it != 0L },
        messageId = messageId.takeIf { it != 0L },
        sender = senderId?.toDomain(),
        senderName = senderName.takeIf { it.isNotEmpty() },
        date = date.takeIf { it != 0 },
        isOutgoing = isOutgoing
    )

fun TdApi.MessageImportInfo.toDomain(): Message.MessageImportInfo =
    Message.MessageImportInfo(
        senderName = senderName,
        date = date
    )

fun TdApi.FactCheck.toDomain(): FactCheck =
    FactCheck(
        text = text.toDomain(),
        countryCode = countryCode
    )

