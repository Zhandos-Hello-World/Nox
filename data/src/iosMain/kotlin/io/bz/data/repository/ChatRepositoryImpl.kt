package io.bz.data.repository

import io.bz.data.lib.TdClientManager
import io.bz.domain.core.DomainResult
import io.bz.domain.model.chat.ChatListType
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.Messages
import io.bz.domain.repository.ChatRepository
import io.bz.domain.stores.ChatStore
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.koin.core.logger.Logger

class ChatRepositoryImpl(
    val clientManager: TdClientManager,
    val store: ChatStore,
) : ChatRepository {
    override val allChats: Flow<List<ChatModel>> = store.state

    override suspend fun loadChats(
        chatListType: ChatListType,
        limit: Int,
    ): DomainResult<Unit> {
        val jsonQuery = """
{
  "@type": "loadChats",
  "chat_list": { "@type": "chatListMain" },
  "limit": 5,
  "@extra": "manual_load"
}
        """.trimIndent()
        return clientManager.send(jsonQuery)
    }

    override suspend fun loadChatHistory(
        chatId: Long,
        fromMessageId: Long,
        offset: Int,
        limit: Int,
        onlyLocal: Boolean,
    ) : DomainResult<Messages> {
        val json = """
        {
            "@type": "getChatHistory",
            "chat_id": $chatId,
            "from_message_id": $fromMessageId,
            "offset": $offset,
            "limit": $limit,
            "only_local": $onlyLocal
        }
        """.trimIndent()
        TODO()
//        return clientManager.send(json)
    }
}