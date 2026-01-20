package io.bz.domain.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.chat.ChatListType

interface ChatRepository {
    suspend fun loadChats(
        chatListType: ChatListType,
        limit: Int,
    ): DomainResult<Unit>
}