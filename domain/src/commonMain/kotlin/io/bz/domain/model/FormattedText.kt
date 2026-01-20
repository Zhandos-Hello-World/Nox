package io.bz.domain.model

data class FormattedText(
    val text: String,
    val entities: List<TextEntity>,
)

data class TextEntity(
    val offset: Int,
    val length: Int,
    val type: String,
)