package io.bz.domain.model.chat

data class ChatPosition(
    val list: ChatListType,
    val order: Long,
    val isPinned: Boolean,
    val source: ChatSource?
)
