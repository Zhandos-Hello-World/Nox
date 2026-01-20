package io.bz.domain.model.chat

sealed class StickerFullType {

    /** Regular sticker */
    object Regular : StickerFullType()

    /** Mask sticker */
    data class Mask(
        val maskPosition: MaskPosition?
    ) : StickerFullType() {

        data class MaskPosition(
            val point: MaskPoint,
            val xShift: Double,
            val yShift: Double,
            val scale: Double
        )

        sealed interface MaskPoint {

            object Forehead : MaskPoint
            object Eyes : MaskPoint
            object Mouth : MaskPoint
            object Chin : MaskPoint
        }


    }

    /** Custom emoji sticker */
    data class CustomEmoji(
        val customEmojiId: Long,
        val needsRepainting: Boolean
    ) : StickerFullType()
}
