package io.bz.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Error(
    /** Код ошибки (например, 400, 401, 429). */
    @SerialName("code")
    val code: Int,

    /** Текстовое описание ошибки (например, "MESSAGE_TOO_LONG"). */
    @SerialName("message")
    val message: String
)