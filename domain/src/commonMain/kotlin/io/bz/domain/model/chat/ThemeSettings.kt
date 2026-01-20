package io.bz.domain.model.chat

data class ThemeSettings(
    val baseTheme: BuiltInTheme,
    val accentColor: Int,
    val background: Background?,
    val outgoingMessageFill: Background.BackgroundFill?,
    val animateOutgoingMessageFill: Boolean,
    val outgoingMessageAccentColor: Int
)
