package io.bz.domain.model.chat

import io.bz.domain.model.File
import io.bz.domain.model.Minithumbnail
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChatPhotoInfo(
    @SerialName("small")
    val small: File,
    @SerialName("big")
    val big: File,
    @SerialName("minithumbnail")
    val minithumbnail: Minithumbnail? = null,
    @SerialName("has_animation")
    val hasAnimation: Boolean,
    @SerialName("is_personal")
    val isPersonal: Boolean
)