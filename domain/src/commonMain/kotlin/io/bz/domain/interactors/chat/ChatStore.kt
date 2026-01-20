package io.bz.domain.interactors.chat

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.ChatPosition
import io.bz.domain.model.chat.Message
import io.bz.domain.model.File

class ChatStore {

    private val chats = LinkedHashMap<Long, ChatModel>()
    private val fileIdToChatId = mutableMapOf<Int, Long>()

    private val _state = MutableStateFlow<List<ChatModel>>(emptyList())
    val state: StateFlow<List<ChatModel>> = _state

    fun onNewChat(chat: ChatModel) {
        chat.photo?.small?.id?.let {  fileIdToChatId[it] = chat.id }
        chats[chat.id] = chat
        emit()
    }

    fun updateLastMessage(chatId: Long, lastMessage: Message?) {
        val old = chats[chatId] ?: return
        chats[chatId] = old.copy(lastMessage = lastMessage)
        emit()
    }

    fun updatePosition(chatId: Long, position: ChatPosition?) {
        val old = chats[chatId] ?: return

        val updatedPositions = old.positions
            .filterNot { it.list == position?.list }
            .toMutableList()

        if (position != null) {
            updatedPositions += position
        }

        chats[chatId] = old.copy(positions = updatedPositions.toTypedArray())
        emit()
    }

    fun updateTitle(chatId: Long, title: String) {
        val old = chats[chatId] ?: return
        chats[chatId] = old.copy(title = title)
        emit()
    }

    private fun emit() {
        _state.value = chats.values
            .sortedWith(compareByDescending<ChatModel> {
                // Сначала order из первой позиции, если нет — Long.MIN_VALUE
                it.positions.firstOrNull()?.order ?: Long.MIN_VALUE
            }.thenByDescending {
                // Если order равен, то сортируем по id
                it.id
            })
    }


    fun onUpdateFile(file: File) {
        val fileId = file.id

        val chatId = fileIdToChatId[fileId] ?: return
        val old = chats[chatId] ?: return

        val newSmall = old.photo?.small?.let { oldSmall ->
            if (oldSmall.id == fileId) file else oldSmall
        }

        newSmall?.let {
            val newPhotoInfo = old.photo!!.copy(small = it)
            chats[chatId] = old.copy(photo = newPhotoInfo)
            emit()
        }

    }
}