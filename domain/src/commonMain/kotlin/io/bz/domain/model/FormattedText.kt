package io.bz.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlinx.serialization.json.JsonElement

@Serializable
data class FormattedText(
    @SerialName("text") val text: String,
    @SerialName("entities") val entities: List<TextEntity> = emptyList()
)

@Serializable
data class TextEntity(
    @SerialName("offset") val offset: Int,
    @SerialName("length") val length: Int,
    @SerialName("type") val type: TextEntityType
)

@Serializable
@JsonClassDiscriminator("@type") // TDLib использует это поле для определения типа
sealed interface TextEntityType {
    @Serializable @SerialName("textEntityTypeMention") data object Mention : TextEntityType
    @Serializable @SerialName("textEntityTypeHashtag") data object Hashtag : TextEntityType
    @Serializable
    @SerialName("textEntityTypeBold")
    data object Bold : TextEntityType

    @Serializable
    @SerialName("textEntityTypeCustomEmoji")
    data class CustomEmoji(
        @SerialName("custom_emoji_id") val customEmojiId: Long
    ) : TextEntityType
    @Serializable @SerialName("textEntityTypeItalic") data object Italic : TextEntityType
    // И так далее...

    // ПРЕДОХРАНИТЕЛЬ: если Telegram пришлет новый тип, который вы не описали
    @Serializable
    data class Unknown(val json: JsonElement? = null) : TextEntityType
}