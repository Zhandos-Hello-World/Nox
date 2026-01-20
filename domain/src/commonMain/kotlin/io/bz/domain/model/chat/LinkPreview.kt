package io.bz.domain.model.chat

import io.bz.domain.model.FormattedText

data class LinkPreview(
    val url: String,
    val displayUrl: String,
    val siteName: String,
    val title: String,
    val description: FormattedText,
    val author: String,
    val type: LinkPreviewType,
    val hasLargeMedia: Boolean,
    val showLargeMedia: Boolean,
    val showMediaAboveDescription: Boolean,
    val skipConfirmation: Boolean,
    val showAboveText: Boolean,
    val instantViewVersion: Int
) {

    sealed interface LinkPreviewType {
        object LinkPreviewTypeArticle : LinkPreviewType
        object LinkPreviewTypePhoto : LinkPreviewType
        object LinkPreviewTypeVideo : LinkPreviewType
        object LinkPreviewTypeAnimation : LinkPreviewType
        object LinkPreviewTypeAudio : LinkPreviewType
        object LinkPreviewTypeDocument : LinkPreviewType
        object LinkPreviewTypeUnknown : LinkPreviewType
    }
}