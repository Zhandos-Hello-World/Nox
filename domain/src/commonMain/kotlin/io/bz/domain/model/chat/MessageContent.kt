package io.bz.domain.model.chat

import io.bz.domain.model.FormattedText

sealed interface MessageContent {
    data class MessageText(
        val text: FormattedText,
        val linkPreview: LinkPreview?,
        val linkPreviewOptions: LinkPreviewOptions?
    ) : MessageContent

    data class UnSupportedContent(val name: String) : MessageContent
}
