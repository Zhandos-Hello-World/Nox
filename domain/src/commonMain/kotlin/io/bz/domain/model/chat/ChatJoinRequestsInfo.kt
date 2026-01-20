package io.bz.domain.model.chat

data class ChatJoinRequestsInfo(
    val totalCount: Int,
    val userIds: List<Long>
)
