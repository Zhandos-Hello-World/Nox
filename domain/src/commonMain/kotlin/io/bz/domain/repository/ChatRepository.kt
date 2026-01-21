package io.bz.domain.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.chat.ChatListType
import io.bz.domain.model.chat.ChatModel
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    val allChats: Flow<List<ChatModel>>

    suspend fun loadChats(
        chatListType: ChatListType,
        limit: Int,
    ): DomainResult<Unit>
}