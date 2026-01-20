package io.bz.domain.model.chat

data class UpgradedGiftBackdrop(
    val id: Int,
    val name: String,
    val colors: UpgradedGiftBackdropColors,
    val rarityPerMille: Int
)

data class UpgradedGiftBackdropColors(
    val centerColor: Int,
    val edgeColor: Int,
    val symbolColor: Int,
    val textColor: Int
)
