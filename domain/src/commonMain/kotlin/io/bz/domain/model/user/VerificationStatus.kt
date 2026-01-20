package io.bz.domain.model.user

data class VerificationStatus(
    val isVerified: Boolean,
    val isScam: Boolean,
    val isFake: Boolean,
    val botVerificationIconCustomEmojiId: Long?
)
