package io.bz.data.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.chat.ChatListType
import io.bz.domain.repository.ChatRepository

class ChatRepositoryImpl(
) : ChatRepository {

    override suspend fun loadChats(
        chatListType: ChatListType,
        limit: Int,
    ): DomainResult<Unit> {
        TODO("Not yet implemented")
    }
}