package io.bz.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatPosition(
    @SerialName("list")
    val list: ChatListType,
    @SerialName("order")
    val order: Long,
    @SerialName("is_pinned")
    val isPinned: Boolean,
    @SerialName("source")
    val source: ChatSource? = null
)
