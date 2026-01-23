package io.bz.nox.features.chats.presentation.list

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val chatsPresentationModule = module {

    viewModel {
        ChatsListViewModel(
            chatService = get(),
        )
    }
}