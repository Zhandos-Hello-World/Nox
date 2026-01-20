package io.bz.domain.state

import io.bz.domain.model.chat.ChatModel

sealed interface ChatState {
    data class Data(
        val chats: Map<Long, ChatModel> = emptyMap(),
    ) : ChatState

    data object None : ChatState
}