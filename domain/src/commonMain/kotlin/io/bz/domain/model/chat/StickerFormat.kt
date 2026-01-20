package io.bz.domain.model.chat

sealed interface StickerFormat {
    object Webp : StickerFormat
    object Tgs : StickerFormat
    object Webm : StickerFormat
}
