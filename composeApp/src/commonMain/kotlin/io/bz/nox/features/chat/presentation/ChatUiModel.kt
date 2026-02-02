package io.bz.nox.features.chat.presentation

import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.Message

data class ChatUiModel(
    val messages: List<Message>,
    val chat: ChatModel,
)