package io.bz.domain.model.chat

data class EmojiStatus(
    val type: EmojiStatusType,
    val expirationDate: Int
) {
    sealed class EmojiStatusType {

        data class EmojiStatusTypeCustomEmoji(
            val customEmojiId: Long
        ) : EmojiStatusType()

        data class EmojiStatusTypeUpgradedGift(
            val upgradedGiftId: Long,
            val giftTitle: String,
            val giftName: String,
            val modelCustomEmojiId: Long,
            val symbolCustomEmojiId: Long,
            val backdropColors: UpgradedGiftBackdropColors
        ) : EmojiStatusType() {

            data class UpgradedGiftBackdropColors(
                val centerColor: Int,
                val edgeColor: Int,
                val symbolColor: Int,
                val textColor: Int
            )

        }
    }

}
