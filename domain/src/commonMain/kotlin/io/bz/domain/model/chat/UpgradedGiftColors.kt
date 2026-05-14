package io.bz.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpgradedGiftColors(
    @SerialName("id")
    val id: Long,
    @SerialName("model_emoji_id")
    val modelEmojiId: Long,
    @SerialName("symbol_emoji_id")
    val symbolEmojiId: Long,
    @SerialName("light_theme")
    val lightTheme: GiftThemeColors,
    @SerialName("dark_theme")
    val darkTheme: GiftThemeColors
) {
    @Serializable
    data class GiftThemeColors(
        @SerialName("accent_color")
        val accentColor: Int,
        @SerialName("colors")
        val colors: List<Int>
    )
}