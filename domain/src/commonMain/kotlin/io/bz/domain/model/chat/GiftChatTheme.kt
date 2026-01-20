package io.bz.domain.model.chat

data class GiftChatTheme(
    val gift: UpgradedGift,
    val lightSettings: ThemeSettings,
    val darkSettings: ThemeSettings
)
