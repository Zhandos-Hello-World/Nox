package io.bz.domain.stores

import io.bz.domain.model.File
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.ChatPosition
import io.bz.domain.model.chat.Message
import kotlinx.coroutines.flow.StateFlow

interface ChatStore {
    val state: StateFlow<List<ChatModel>>

    suspend fun onNewChat(chat: ChatModel)
    fun updateLastMessage(chatId: Long, lastMessage: Message?)
    fun updatePosition(chatId: Long, position: ChatPosition?)
    fun updateTitle(chatId: Long, title: String)
    fun onUpdateFile(file: File)
}