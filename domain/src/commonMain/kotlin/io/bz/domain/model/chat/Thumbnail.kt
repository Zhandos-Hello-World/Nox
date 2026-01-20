package io.bz.domain.model.chat

import io.bz.domain.model.File

data class Thumbnail(
    val format: ThumbnailFormat,
    val width: Int,
    val height: Int,
    val file: File
) {

    sealed interface ThumbnailFormat {
        object Jpeg : ThumbnailFormat
        object Gif : ThumbnailFormat
        object Mpeg4 : ThumbnailFormat
        object Png : ThumbnailFormat
        object Tgs : ThumbnailFormat
        object Webm : ThumbnailFormat
        object Webp : ThumbnailFormat
    }

}
