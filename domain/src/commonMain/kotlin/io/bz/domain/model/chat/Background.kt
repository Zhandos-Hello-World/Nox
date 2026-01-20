package io.bz.domain.model.chat

import io.bz.domain.model.File
import io.bz.domain.model.Minithumbnail

data class Background(
    val id: Long,
    val isDefault: Boolean,
    val isDark: Boolean,
    val name: String,
    val document: Document?,
    val type: BackgroundType
) {

    data class Document(
        val fileName: String,
        val mimeType: String,
        val minithumbnail: Minithumbnail?,
        val thumbnail: Thumbnail?,
        val file: File
    )


    sealed class BackgroundType {

        data class Wallpaper(
            val isBlurred: Boolean,
            val isMoving: Boolean
        ) : BackgroundType()

        data class Pattern(
            val isMoving: Boolean,
            val fill: BackgroundFill,
            val intensity: Int
        ) : BackgroundType()

        data class Fill(
            val fill: BackgroundFill
        ) : BackgroundType()

        data class ChatTheme(
            val themeName: String
        ) : BackgroundType()
    }

    sealed class BackgroundFill {

        data class Solid(
            val color: Int
        ) : BackgroundFill()

        data class Gradient(
            val topColor: Int,
            val bottomColor: Int,
            val rotationAngle: Int
        ) : BackgroundFill()

        data class FreeformGradient(
            val colors: List<Int>,
        ) : BackgroundFill()
    }


}
