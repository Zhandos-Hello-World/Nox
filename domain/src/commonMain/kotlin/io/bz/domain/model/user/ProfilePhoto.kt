package io.bz.domain.model.user

import io.bz.domain.model.File
import io.bz.domain.model.Minithumbnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfilePhoto(
    @SerialName("id")
    val id: Long,
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