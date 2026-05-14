package io.bz.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class RestrictionInfo(
    @SerialName("reason")
    val reason: String? = null,
    @SerialName("has_sensitive_content")
    val hasSensitiveContent: Boolean
) {
    // Поля, помеченные @Transient, игнорируются сериализатором.
    // Это важно для вычисляемых свойств, чтобы они не искали ключ в JSON.
    @Transient
    val isRestricted: Boolean
        get() = !reason.isNullOrBlank() || hasSensitiveContent
}