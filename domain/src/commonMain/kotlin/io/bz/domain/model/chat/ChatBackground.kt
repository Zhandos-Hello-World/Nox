package io.bz.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatBackground(
    @SerialName("background") val background: Background,
    @SerialName("dark_theme_dimming") val darkThemeDimming: Int
)