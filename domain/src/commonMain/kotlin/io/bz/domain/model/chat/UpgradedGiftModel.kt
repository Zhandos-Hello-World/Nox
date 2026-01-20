package io.bz.domain.model.chat

data class UpgradedGiftModel(
    val name: String,
    val sticker: Sticker,
    val rarityPerMille: Int
)