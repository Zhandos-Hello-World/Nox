package io.bz.domain.model.chat

import io.bz.domain.model.File
import io.bz.domain.model.Minithumbnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
data class Background(
    @SerialName("id") val id: Long,
    @SerialName("is_default") val isDefault: Boolean,
    @SerialName("is_dark") val isDark: Boolean,
    @SerialName("name") val name: String,
    @SerialName("document") val document: Document? = null,
    @SerialName("type") val type: BackgroundType
) {
    @Serializable
    data class Document(
        @SerialName("file_name") val fileName: String,
        @SerialName("mime_type") val mimeType: String,
        @SerialName("minithumbnail") val minithumbnail: Minithumbnail? = null,
        @SerialName("thumbnail") val thumbnail: Thumbnail? = null,
        @SerialName("document") val file: File? = null,
    )


    @Serializable
    @JsonClassDiscriminator("@type")
    sealed class BackgroundType {

        @Serializable
        @SerialName("backgroundTypeWallpaper")
        data class Wallpaper(
            @SerialName("is_blurred") val isBlurred: Boolean,
            @SerialName("is_moving") val isMoving: Boolean
        ) : BackgroundType()

        @Serializable
        @SerialName("backgroundTypePattern")
        data class Pattern(
            @SerialName("is_moving") val isMoving: Boolean,
            @SerialName("fill") val fill: BackgroundFill,
            @SerialName("intensity") val intensity: Int
        ) : BackgroundType()

        @Serializable
        @SerialName("backgroundTypeFill")
        data class Fill(
            @SerialName("fill") val fill: BackgroundFill
        ) : BackgroundType()

        @Serializable
        @SerialName("backgroundTypeChatTheme")
        data class ChatTheme(
            @SerialName("theme_name") val themeName: String
        ) : BackgroundType()
    }

    @Serializable
    @JsonClassDiscriminator("@type")
    sealed class BackgroundFill {

        @Serializable
        @SerialName("backgroundFillSolid")
        data class Solid(
            @SerialName("color") val color: Int
        ) : BackgroundFill()

        @Serializable
        @SerialName("backgroundFillGradient")
        data class Gradient(
            @SerialName("top_color") val topColor: Int,
            @SerialName("bottom_color") val bottomColor: Int,
            @SerialName("rotation_angle") val rotationAngle: Int
        ) : BackgroundFill()

        @Serializable
        @SerialName("backgroundFillFreeformGradient")
        data class FreeformGradient(
            @SerialName("colors") val colors: List<Int>
        ) : BackgroundFill()
    }


}
