package io.bz.data.stores

import io.bz.domain.model.File
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.ChatPosition
import io.bz.domain.model.chat.Message
import io.bz.domain.stores.ChatStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ChatStoreImpl : ChatStore {
    private val _state = MutableStateFlow<List<ChatModel>>(emptyList())
    override val state: StateFlow<List<ChatModel>> = _state
    private val chats = LinkedHashMap<Long, ChatModel>()
    private val fileIdToChatId = mutableMapOf<Int, Long>()

    private val mutex = Mutex()

    override suspend fun onNewChat(chat: ChatModel) {
        mutex.withLock {
            chat.photo?.small?.id?.let { fileIdToChatId[it] = chat.id }
            chats[chat.id] = chat
            emit()
        }
    }

    override fun updateLastMessage(chatId: Long, lastMessage: Message?) {
        val old = chats[chatId] ?: return
        chats[chatId] = old.copy(lastMessage = lastMessage)
        emit()
    }

    override fun updatePosition(chatId: Long, position: ChatPosition?) {
        val old = chats[chatId] ?: return

        val updatedPositions = old.positions.filterNot { it.list == position?.list }.toMutableList()

        if (position != null) {
            updatedPositions += position
        }

        chats[chatId] = old.copy(positions = updatedPositions.toTypedArray())
        emit()
    }

    override fun updateTitle(chatId: Long, title: String) {
        val old = chats[chatId] ?: return
        chats[chatId] = old.copy(title = title)
        emit()
    }


    override fun onUpdateFile(file: File) {
        val fileId = file.id

        val chatId = fileIdToChatId[fileId] ?: return
        val old = chats[chatId] ?: return

        val newSmall = old.photo?.small?.let { oldSmall ->
            if (oldSmall.id == fileId) file else oldSmall
        }

        newSmall?.let {
            val newPhotoInfo = old.photo?.copy(small = it)
            chats[chatId] = old.copy(photo = newPhotoInfo)
            emit()
        }
    }

    private fun emit() {
        _state.value = chats.values.sortedWith(
            compareByDescending<ChatModel> {
                // Сначала order из первой позиции, если нет — Long.MIN_VALUE
                it.positions.firstOrNull()?.order ?: Long.MIN_VALUE
            }.thenByDescending {
                // Если order равен, то сортируем по id
                it.id
            },
        )
    }
}