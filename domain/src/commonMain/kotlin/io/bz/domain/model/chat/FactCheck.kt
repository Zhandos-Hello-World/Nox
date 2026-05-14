package io.bz.domain.model.chat

import io.bz.domain.model.FormattedText
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FactCheck(
    @SerialName("text") val text: FormattedText,
    @SerialName("country_code") val countryCode: String
)