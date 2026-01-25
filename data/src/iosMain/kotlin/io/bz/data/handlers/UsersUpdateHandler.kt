package io.bz.data.handlers

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import io.bz.domain.model.user.ProfilePhoto
import io.bz.domain.model.user.User
import io.bz.domain.model.user.UserStatus
import io.bz.domain.model.user.UserType
import io.bz.domain.model.user.Usernames
import io.bz.domain.stores.UserStore

class UsersUpdateHandler(
    private val userStore: UserStore,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        return true
    }

    suspend fun readyTestUser() {
        userStore.updateUser(
            user = User(
                id = 123456789L,
        firstName = "Zhandos",
        lastName = "Baimurat",
        usernames = null,
        phoneNumber = "+77001234567",
        status = UserStatus.Online(3243), // пример
        profilePhoto = null,
        accentColorId = 5,
        backgroundCustomEmojiId = 0L,
        upgradedGiftColors = null,
        profileAccentColorId = 7,
        profileBackgroundCustomEmojiId = 0L,
        emojiStatus = null,
        isContact = true,
        isMutualContact = true,
        isCloseFriend = false,
        verificationStatus = null,
        isPremium = true,
        isSupport = false,
        restrictionInfo = null,
        activeStoryState = null,
        restrictsNewChats = false,
        paidMessageStarCount = 0L,
        haveAccess = true,
        type = UserType.Regular, // пример
        languageCode = "ru",
        addedToAttachmentMenu = false
        )

        )
    }
}