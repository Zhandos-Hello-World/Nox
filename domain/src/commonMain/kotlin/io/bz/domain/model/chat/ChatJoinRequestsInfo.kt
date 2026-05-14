package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChatJoinRequestsInfo(
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("user_ids")
    val userIds: List<Long> = emptyList()
)