package io.bz.domain.model.chat

import io.bz.domain.model.File
import io.bz.domain.model.Minithumbnail

data class ChatPhotoInfo(
    val small: File,
    val big: File,
    val minithumbnail: Minithumbnail?,
    val hasAnimation: Boolean,
    val isPersonal: Boolean,
)