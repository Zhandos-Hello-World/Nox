package io.bz.domain.model.user

import io.bz.domain.model.File
import io.bz.domain.model.Minithumbnail

data class ProfilePhoto(
    val id: Long,
    val small: File,
    val big: File,
    val minithumbnail: Minithumbnail?,
    val hasAnimation: Boolean,
    val isPersonal: Boolean
)
