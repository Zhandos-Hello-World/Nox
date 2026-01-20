package io.bz.domain.model.chat

data class UpgradedGiftColors(
    val id: Long,
    val modelEmojiId: Long,
    val symbolEmojiId: Long,
    val lightTheme: GiftThemeColors,
    val darkTheme: GiftThemeColors
) {
    data class GiftThemeColors(
        val accentColor: Int,
        val colors: List<Int>
    )
}