package io.bz.data.mapper.user

import org.drinkless.tdlib.TdApi
import io.bz.data.mapper.chat.toDomain
import io.bz.domain.model.user.ActiveStoryState
import io.bz.domain.model.user.ProfilePhoto
import io.bz.domain.model.user.User
import io.bz.domain.model.user.UserStatus
import io.bz.domain.model.user.UserType
import io.bz.domain.model.user.Usernames
import io.bz.domain.model.user.VerificationStatus


class UserDtoMapper {

    fun map(user: TdApi.User): User {
        return user.toDomain()
    }
}

fun TdApi.User.toDomain(): User =
    User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        usernames = usernames?.toDomain(),
        phoneNumber = phoneNumber,
        status = status.toDomain(),
        profilePhoto = profilePhoto?.toDomain(),
        accentColorId = accentColorId,
        backgroundCustomEmojiId = backgroundCustomEmojiId,
        upgradedGiftColors = upgradedGiftColors?.toDomain(),
        profileAccentColorId = profileAccentColorId,
        profileBackgroundCustomEmojiId = profileBackgroundCustomEmojiId,
        emojiStatus = emojiStatus?.toDomain(),
        isContact = isContact,
        isMutualContact = isMutualContact,
        isCloseFriend = isCloseFriend,
        verificationStatus = verificationStatus?.toDomain(),
        isPremium = isPremium,
        isSupport = isSupport,
        restrictionInfo = restrictionInfo?.toDomain(),
        activeStoryState = activeStoryState?.toDomain(),
        restrictsNewChats = restrictsNewChats,
        paidMessageStarCount = paidMessageStarCount,
        haveAccess = haveAccess,
        type = type.toDomain(),
        languageCode = languageCode,
        addedToAttachmentMenu = addedToAttachmentMenu
    )

fun TdApi.UserStatus.toDomain(): UserStatus =
    when (this) {
        is TdApi.UserStatusEmpty ->
            UserStatus.Empty

        is TdApi.UserStatusOnline ->
            UserStatus.Online(
                expires = expires
            )

        is TdApi.UserStatusOffline ->
            UserStatus.Offline(
                wasOnline = wasOnline
            )

        is TdApi.UserStatusRecently ->
            UserStatus.Recently(
                byMyPrivacySettings = byMyPrivacySettings
            )

        is TdApi.UserStatusLastWeek ->
            UserStatus.LastWeek(
                byMyPrivacySettings = byMyPrivacySettings
            )

        is TdApi.UserStatusLastMonth ->
            UserStatus.LastMonth(
                byMyPrivacySettings = byMyPrivacySettings
            )

        else -> error("Unknown UserStatus: $this")
    }

fun TdApi.ProfilePhoto.toDomain(): ProfilePhoto =
    ProfilePhoto(
        id = id,
        small = small.toDomain(),
        big = big.toDomain(),
        minithumbnail = minithumbnail?.toDomain(),
        hasAnimation = hasAnimation,
        isPersonal = isPersonal
    )

fun TdApi.Usernames.toDomain(): Usernames =
    Usernames(
        activeUsernames = activeUsernames?.toList().orEmpty(),
        disabledUsernames = disabledUsernames?.toList().orEmpty(),
        editableUsername = editableUsername.takeIf { it.isNotBlank() },
        collectibleUsernames = collectibleUsernames?.toList().orEmpty()
    )

fun TdApi.VerificationStatus.toDomain(): VerificationStatus =
    VerificationStatus(
        isVerified = isVerified,
        isScam = isScam,
        isFake = isFake,
        botVerificationIconCustomEmojiId =
            botVerificationIconCustomEmojiId.takeIf { it != 0L }
    )

fun TdApi.UserType.toDomain(): UserType =
    when (this) {
        is TdApi.UserTypeRegular ->
            UserType.Regular

        is TdApi.UserTypeDeleted ->
            UserType.Deleted

        is TdApi.UserTypeUnknown ->
            UserType.Unknown

        is TdApi.UserTypeBot ->
            UserType.Bot(
                canBeEdited = canBeEdited,
                canJoinGroups = canJoinGroups,
                canReadAllGroupMessages = canReadAllGroupMessages,
                hasMainWebApp = hasMainWebApp,
                hasTopics = hasTopics,
                isInline = isInline,
                inlineQueryPlaceholder =
                    inlineQueryPlaceholder.takeIf { it.isNotBlank() },
                needLocation = needLocation,
                canConnectToBusiness = canConnectToBusiness,
                canBeAddedToAttachmentMenu = canBeAddedToAttachmentMenu,
                activeUserCount = activeUserCount
            )

        else -> error("Unknown UserType: $this")
    }

fun TdApi.ActiveStoryState.toDomain(): ActiveStoryState =
    when (this) {
        is TdApi.ActiveStoryStateLive ->
            ActiveStoryState.Live(
                storyId = storyId
            )

        is TdApi.ActiveStoryStateUnread ->
            ActiveStoryState.Unread

        is TdApi.ActiveStoryStateRead ->
            ActiveStoryState.Read

        else -> error("Unknown ActiveStoryState: $this")
    }
