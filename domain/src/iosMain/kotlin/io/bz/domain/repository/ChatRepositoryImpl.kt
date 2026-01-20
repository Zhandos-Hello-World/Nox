package io.bz.domain.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.chat.ChatListType

class ChatRepositoryImpl(
) : ChatRepository {

    override suspend fun loadChats(
        chatListType: ChatListType,
        limit: Int,
    ): DomainResult<Unit> {
        TODO("Not yet implemented")
    }
}