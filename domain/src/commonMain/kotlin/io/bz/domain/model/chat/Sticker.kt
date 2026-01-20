package io.bz.domain.model.chat

import io.bz.domain.model.File

data class Sticker(
    val id: Long,
    val setId: Long,
    val width: Int,
    val height: Int,
    val emoji: String,
    val format: StickerFormat,
    val fullType: StickerFullType,
    val thumbnail: Thumbnail?,
    val sticker: File
)
