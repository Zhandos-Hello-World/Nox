package io.bz.data.interactors

import io.bz.domain.interactors.chat.ChatIntent
import io.bz.domain.interactors.chat.ChatService
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.repository.ChatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ChatServiceImpl(
    private val repository: ChatRepository,
    coroutineScope: CoroutineScope,
) : ChatService {


    override val state: StateFlow<LinkedHashMap<Long, ChatModel>> =
        repository.allChats.map { chats ->
            LinkedHashMap<Long, ChatModel>().apply {
                chats.forEach { chat ->
                    put(chat.id, chat)
                }
            }
        }.stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = linkedMapOf()
        )

    override suspend fun sendIntent(intent: ChatIntent) {
        when (intent) {
            is ChatIntent.LoadChats -> repository.loadChats(
                chatListType = intent.type,
                limit = intent.limit,
            )

            is ChatIntent.LoadDirectMessagesChatTopics -> TODO("IMPLEMENT")
        }
    }
}