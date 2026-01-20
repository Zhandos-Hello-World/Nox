package io.bz.domain.repository

import io.bz.domain.TdClientManager
import io.bz.domain.core.DomainResult
import io.bz.domain.core.tdLibUnitCall
import io.bz.domain.lib.TdApi
import io.bz.domain.model.chat.ChatListType

class ChatRepositoryImpl(
    val clientManager: TdClientManager,
) : ChatRepository {

    override suspend fun loadChats(
        chatListType: ChatListType,
        limit: Int,
    ): DomainResult<Unit> {
        val type = when (chatListType) {
            ChatListType.Archive -> TdApi.ChatListArchive()
            is ChatListType.Folder -> TdApi.ChatListFolder()
            else -> TdApi.ChatListMain()
        }
        return tdLibUnitCall(
            client = clientManager.client,
            request = TdApi.LoadChats(
                type,
                limit,
            ),
        )
    }
}