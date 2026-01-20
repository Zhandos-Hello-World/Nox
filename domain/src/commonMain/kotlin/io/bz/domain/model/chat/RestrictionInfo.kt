package io.bz.domain.model.chat

data class RestrictionInfo(
    val reason: String?,
    val hasSensitiveContent: Boolean
) {
    val isRestricted: Boolean
        get() = !reason.isNullOrBlank() || hasSensitiveContent
}
