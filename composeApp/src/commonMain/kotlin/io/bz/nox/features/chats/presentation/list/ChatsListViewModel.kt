package io.bz.nox.features.chats.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.chat.ChatIntent
import io.bz.domain.interactors.chat.ChatService
import io.bz.domain.model.chat.ChatListType
import io.bz.domain.model.chat.Messages
import io.bz.domain.state.ChatState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatsListViewModel(
    private val chatService: ChatService,
) : ViewModel() {
    val state: MutableStateFlow<ChatState> = MutableStateFlow(ChatState.None)

    init {
        viewModelScope.launch {
            chatService.state.map { chats ->
                ChatState.Data(chats)
            }.collect { newState ->
                state.update { newState }
            }
        }
    }

    fun getChats() {
        viewModelScope.launch {
            chatService.sendIntent<Unit>(
                ChatIntent.LoadChats(
                    type = ChatListType.Main,
                    limit = 100,
                ),
            )
        }
    }
}