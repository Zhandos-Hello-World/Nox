package io.bz.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmojiStatus(
    @SerialName("type")
    val type: EmojiStatusType,
    @SerialName("expiration_date")
    val expirationDate: Int
) {
    @Serializable
    sealed class EmojiStatusType {

        @Serializable
        @SerialName("emojiStatusTypeCustomEmoji")
        data class EmojiStatusTypeCustomEmoji(
            @SerialName("custom_emoji_id")
            val customEmojiId: Long
        ) : EmojiStatusType()

        @Serializable
        @SerialName("emojiStatusTypeUpgradedGift")
        data class EmojiStatusTypeUpgradedGift(
            @SerialName("upgraded_gift_id")
            val upgradedGiftId: Long,
            @SerialName("gift_title")
            val giftTitle: String,
            @SerialName("gift_name")
            val giftName: String,
            @SerialName("model_custom_emoji_id")
            val modelCustomEmojiId: Long,
            @SerialName("symbol_custom_emoji_id")
            val symbolCustomEmojiId: Long,
            @SerialName("backdrop_colors")
            val backdropColors: UpgradedGiftBackdropColors
        ) : EmojiStatusType() {

            @Serializable
            data class UpgradedGiftBackdropColors(
                @SerialName("center_color")
                val centerColor: Int,
                @SerialName("edge_color")
                val edgeColor: Int,
                @SerialName("symbol_color")
                val symbolColor: Int,
                @SerialName("text_color")
                val textColor: Int
            )
        }
    }
}