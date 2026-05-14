package io.bz.data.di

import io.bz.data.core.TdUpdateHandler
import io.bz.data.core.TdUpdatesProcessor
import io.bz.data.handlers.AuthUpdateHandler
import io.bz.data.handlers.ChatUpdateHandler
import io.bz.data.handlers.UsersUpdateHandler
import io.bz.data.lib.TdClientManager
import io.bz.data.repository.AuthRepositoryImpl
import io.bz.data.repository.ChatRepositoryImpl
import io.bz.data.repository.FileRepositoryImpl
import io.bz.data.repository.UserRepositoryImpl
import io.bz.domain.repository.AuthRepository
import io.bz.domain.repository.ChatRepository
import io.bz.domain.repository.FileRepository
import io.bz.domain.repository.UserRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<TdUpdateHandler>(named("auth")) { AuthUpdateHandler(get()) }
    single<TdUpdateHandler>(named("chat")) { ChatUpdateHandler(get()) }
    single<TdUpdateHandler>(named("users")) { UsersUpdateHandler(get()) }


    single<TdUpdatesProcessor>(createdAtStart = true) {
        TdUpdatesProcessor(
            handlers = listOf(
                get(named("auth")),
                get(named("chat")),
                get(named("users")),
            ),
        )
    }

    single {
        TdClientManager(
            scope = get(),
            processor = get(),
        )
    }

    single<ChatRepository> {
        ChatRepositoryImpl(
            clientManager = get(),
            store = get(),
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            store = get(),
            clientManager = get(),
        )
    }

    single<FileRepository> {
        FileRepositoryImpl(
            store = get(),
            clientManager = get(),
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
            userStore = get(),
            clientManager = get(),
        )
    }

}