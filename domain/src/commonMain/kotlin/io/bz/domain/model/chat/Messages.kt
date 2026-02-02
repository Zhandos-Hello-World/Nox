package io.bz.domain.model.chat

data class Messages(
    val totalCount: Int,
    val messages: List<Message>,
)