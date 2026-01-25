package io.bz.data.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.chat.ChatListType
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.repository.ChatRepository
import io.bz.domain.stores.ChatStore
import io.bz.domain.stores.FileStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChatRepositoryImpl(
    val store: ChatStore,
    val fileStore: FileStore,
    val scope: CoroutineScope,
) : ChatRepository {
    override val allChats: Flow<List<ChatModel>> = store.state

    init {
        observeFiles()
    }

    private fun observeFiles() {
        combine(
            store.state,      // List<ChatEntity>
            fileStore.files   // Map<FileId, FileModel>
        ) { chats, files ->
            chats to files
        }
            .onEach { (chats, files) ->
                files.forEach {
                    store.onUpdateFile(it)
                }
            }
            .launchIn(scope) // scope репозитория
    }

    override suspend fun loadChats(
        chatListType: ChatListType,
        limit: Int,
    ): DomainResult<Unit> {
        observeFiles()
        return DomainResult.Success(Unit)
    }
}