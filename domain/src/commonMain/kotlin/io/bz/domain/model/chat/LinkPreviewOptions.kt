package io.bz.domain.model.chat

data class LinkPreviewOptions(
    val isDisabled: Boolean,
    val url: String,
    val forceSmallMedia: Boolean,
    val forceLargeMedia: Boolean,
    val showAboveText: Boolean
)
