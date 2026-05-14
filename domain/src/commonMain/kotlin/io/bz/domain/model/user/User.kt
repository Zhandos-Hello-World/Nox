package io.bz.domain.model.user

import io.bz.domain.model.chat.EmojiStatus
import io.bz.domain.model.chat.UpgradedGiftColors
import io.bz.domain.model.chat.RestrictionInfo

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class User(
    @SerialName("id")
    val id: Long,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String,

    @SerialName("usernames")
    val usernames: Usernames? = null,

    @SerialName("phone_number")
    val phoneNumber: String,

    @SerialName("status")
    val status: UserStatus,

    @SerialName("profile_photo")
    val profilePhoto: ProfilePhoto? = null,

    @SerialName("accent_color_id")
    val accentColorId: Int,

    @SerialName("background_custom_emoji_id")
    val backgroundCustomEmojiId: Long,

    @SerialName("upgraded_gift_colors")
    val upgradedGiftColors: UpgradedGiftColors? = null,

    @SerialName("profile_accent_color_id")
    val profileAccentColorId: Int,

    @SerialName("profile_background_custom_emoji_id")
    val profileBackgroundCustomEmojiId: Long,

    @SerialName("emoji_status")
    val emojiStatus: EmojiStatus? = null,

    @SerialName("is_contact")
    val isContact: Boolean,

    @SerialName("is_mutual_contact")
    val isMutualContact: Boolean,

    @SerialName("is_close_friend")
    val isCloseFriend: Boolean,

    @SerialName("verification_status")
    val verificationStatus: VerificationStatus? = null,

    @SerialName("is_premium")
    val isPremium: Boolean,

    @SerialName("is_support")
    val isSupport: Boolean,

    @SerialName("restriction_info")
    val restrictionInfo: RestrictionInfo? = null,

    @SerialName("active_story_state")
    val activeStoryState: ActiveStoryState? = null,

    @SerialName("restricts_new_chats")
    val restrictsNewChats: Boolean,

    @SerialName("paid_message_star_count")
    val paidMessageStarCount: Long,

    @SerialName("have_access")
    val haveAccess: Boolean,

    @SerialName("type")
    val type: UserType,

    @SerialName("language_code")
    val languageCode: String,

    @SerialName("added_to_attachment_menu")
    val addedToAttachmentMenu: Boolean
)