package io.bz.domain.model.chat

import io.bz.domain.model.File
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
data class Thumbnail(
    @SerialName("format") val format: ThumbnailFormat,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("file") val file: File // Твоя модель файла
) {

    @Serializable
    @JsonClassDiscriminator("@type")
    sealed interface ThumbnailFormat {

        @Serializable
        @SerialName("thumbnailFormatJpeg")
        object Jpeg : ThumbnailFormat

        @Serializable
        @SerialName("thumbnailFormatGif")
        object Gif : ThumbnailFormat

        @Serializable
        @SerialName("thumbnailFormatMpeg4")
        object Mpeg4 : ThumbnailFormat

        @Serializable
        @SerialName("thumbnailFormatPng")
        object Png : ThumbnailFormat

        @Serializable
        @SerialName("thumbnailFormatTgs")
        object Tgs : ThumbnailFormat

        @Serializable
        @SerialName("thumbnailFormatWebm")
        object Webm : ThumbnailFormat

        @Serializable
        @SerialName("thumbnailFormatWebp")
        object Webp : ThumbnailFormat
    }

}
