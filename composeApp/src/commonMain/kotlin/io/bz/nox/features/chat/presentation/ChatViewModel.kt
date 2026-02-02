package io.bz.nox.features.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.chat.ChatIntent
import io.bz.domain.interactors.chat.ChatService
import io.bz.domain.model.chat.Message
import io.bz.domain.model.chat.Messages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatService: ChatService,
) : ViewModel()  {
    val state = MutableStateFlow<List<Message>>(emptyList())

    fun getMessages(chatID: Long) {
        viewModelScope.launch {
            val messages = chatService.sendIntent<Messages>(
                ChatIntent.LoadChatHistory(
                    chatId = chatID,
                    fromMessageId = 0,
                    offset = 0,
                    limit = 50,
                    onlyLocal = false,
                ),
            )
            when (val messagesState = messages) {
                is DomainResult.Failure -> {

                }
                is DomainResult.Success<Messages> -> state.emit( messagesState.value.messages.reversed())
            }
        }
    }
}