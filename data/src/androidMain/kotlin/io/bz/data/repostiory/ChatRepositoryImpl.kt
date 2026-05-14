package io.bz.data.repostiory

import io.bz.data.core.tdLibCall
import io.bz.data.core.tdLibMapper
import io.bz.data.lib.TdClientManager
import io.bz.data.core.tdLibUnitCall
import io.bz.data.mapper.chat.toDomain
import org.drinkless.tdlib.TdApi
import io.bz.domain.core.DomainResult
import io.bz.domain.model.chat.ChatListType
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.Message
import io.bz.domain.model.chat.Messages
import io.bz.domain.repository.ChatRepository
import io.bz.domain.stores.ChatStore
import io.bz.domain.stores.FileStore
import kotlinx.coroutines.CoroutineScope
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

    override suspend fun loadChatHistory(
        chatId: Long,
        fromMessageId: Long,
        offset: Int,
        limit: Int,
        onlyLocal: Boolean,
    ) : DomainResult<Messages> {
        return tdLibCall(
            client = clientManager.client,
            request = TdApi.GetChatHistory(
                chatId,
                fromMessageId,
                offset,
                limit,
                onlyLocal,
            )
        ).tdLibMapper {
            val res = when (val res = it) {
                is DomainResult.Failure -> DomainResult.Failure(res.error)
                is DomainResult.Success<*> -> {
                    val messages = res.value as TdApi.Messages
                    DomainResult.Success(messages.toDomain())
                }
            }
            res
        }
    }
}