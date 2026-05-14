package io.bz.domain.model

import io.bz.domain.core.serializer.Base64ByteArraySerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Minithumbnail(
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int,
    @Serializable(with = Base64ByteArraySerializer::class)
    @SerialName("data")
    val data: ByteArray,
)