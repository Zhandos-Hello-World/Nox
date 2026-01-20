package io.bz.domain.interactors.chat

import kotlinx.coroutines.flow.StateFlow
import io.bz.domain.model.chat.ChatModel

interface ChatService {
    val state: StateFlow<LinkedHashMap<Long, ChatModel>>

    suspend fun sendIntent(intent: ChatIntent)
}