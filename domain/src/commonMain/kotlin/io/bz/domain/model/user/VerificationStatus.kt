package io.bz.domain.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerificationStatus(
    @SerialName("is_verified")
    val isVerified: Boolean,
    @SerialName("is_scam")
    val isScam: Boolean,
    @SerialName("is_fake")
    val isFake: Boolean,
    @SerialName("bot_verification_icon_custom_emoji_id")
    val botVerificationIconCustomEmojiId: Long? = null
)