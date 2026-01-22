package io.bz.data.di

import io.bz.data.lib.TdClientManager
import io.bz.data.repostiory.AuthRepositoryImpl
import io.bz.data.repostiory.ChatRepositoryImpl
import io.bz.data.repostiory.FileRepositoryImpl
import io.bz.data.stores.ChatStoreImpl
import io.bz.domain.repository.AuthRepository
import io.bz.domain.repository.ChatRepository
import io.bz.domain.repository.FileRepository
import io.bz.domain.stores.ChatStore
import org.koin.dsl.module

val dataModule = module {

    single { TdClientManager() }

    single<ChatStore> { ChatStoreImpl() }

    single<ChatRepository> {
        ChatRepositoryImpl(
            clientManager = get(),
            store = get(),
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            clientManager = get(),
        )
    }

    single<FileRepository> {
        FileRepositoryImpl(
            clientManager = get(),
        )
    }
}