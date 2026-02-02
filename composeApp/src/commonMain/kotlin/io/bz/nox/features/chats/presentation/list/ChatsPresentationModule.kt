package io.bz.nox.features.chats.presentation.list

import io.bz.nox.features.chat.presentation.ChatViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val chatsPresentationModule = module {

    viewModel {
        ChatsListViewModel(
            chatService = get(),
        )
    }

    viewModel {
        ChatViewModel(
            chatService = get(),
        )
    }
}