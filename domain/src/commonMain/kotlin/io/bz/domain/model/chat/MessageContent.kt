package io.bz.domain.model.chat

import io.bz.domain.core.serializer.MessageContentSerializer
import io.bz.domain.model.FormattedText
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MessageContentSerializer::class)
@SerialName("messageText")
sealed interface MessageContent {

    @Serializable
    @SerialName("messageText")
    data class MessageText(
        val text: FormattedText,
//        val link_preview: LinkPreview? = null, // В TDLib обычно snake_case
//        val link_preview_options: LinkPreviewOptions? = null
    ) : MessageContent

    @Serializable
    @SerialName("messageContactRegistered")
    data object MessageContactRegistered : MessageContent

    @Serializable
    data class UnSupportedContent(
        val rawJson: kotlinx.serialization.json.JsonElement? = null
    ) : MessageContent
}