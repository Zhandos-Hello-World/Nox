package io.bz.domain.model.chat

import io.bz.domain.model.FormattedText

data class FactCheck(
    val text: FormattedText,
    val countryCode: String
)