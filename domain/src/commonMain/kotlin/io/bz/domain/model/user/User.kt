package io.bz.domain.model.user

import io.bz.domain.model.chat.EmojiStatus
import io.bz.domain.model.chat.UpgradedGiftColors
import io.bz.domain.model.chat.RestrictionInfo

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val usernames: Usernames?,
    val phoneNumber: String,
    val status: UserStatus,
    val profilePhoto: ProfilePhoto?,
    val accentColorId: Int,
    val backgroundCustomEmojiId: Long,
    val upgradedGiftColors: UpgradedGiftColors?,
    val profileAccentColorId: Int,
    val profileBackgroundCustomEmojiId: Long,
    val emojiStatus: EmojiStatus?,
    val isContact: Boolean,
    val isMutualContact: Boolean,
    val isCloseFriend: Boolean,
    val verificationStatus: VerificationStatus?,
    val isPremium: Boolean,
    val isSupport: Boolean,
    val restrictionInfo: RestrictionInfo?,
    val activeStoryState: ActiveStoryState?,
    val restrictsNewChats: Boolean,
    val paidMessageStarCount: Long,
    val haveAccess: Boolean,
    val type: UserType,
    val languageCode: String,
    val addedToAttachmentMenu: Boolean
)