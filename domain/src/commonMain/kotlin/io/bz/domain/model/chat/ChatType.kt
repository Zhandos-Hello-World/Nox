package io.bz.domain.model.chat

import io.bz.domain.core.serializer.ChatTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ChatTypeSerializer::class)
sealed interface ChatType {
    @Serializable @SerialName("chatTypePrivate") object Private : ChatType
    @Serializable @SerialName("chatTypeBasicGroup") object BasicGroup : ChatType
    @Serializable @SerialName("chatTypeSupergroup") object Supergroup : ChatType
    @Serializable @SerialName("chatTypeSecret") object Secret : ChatType

    // Этот парень спасет тебя от крэша
    data class Unknown(val rawType: String) : ChatType
}