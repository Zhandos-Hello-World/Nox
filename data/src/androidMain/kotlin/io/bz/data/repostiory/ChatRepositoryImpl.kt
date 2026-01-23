package io.bz.data.repostiory

import io.bz.data.lib.TdClientManager
import io.bz.data.core.tdLibUnitCall
import org.drinkless.tdlib.TdApi
import io.bz.domain.core.DomainResult
import io.bz.domain.model.chat.ChatListType
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.repository.ChatRepository
import io.bz.domain.stores.ChatStore
import kotlinx.coroutines.flow.Flow

class ChatRepositoryImpl(
    val clientManager: TdClientManager,
    val store: ChatStore,
) : ChatRepository {
    override val allChats: Flow<List<ChatModel>> = store.state

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