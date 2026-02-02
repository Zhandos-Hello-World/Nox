package io.bz.data.di

import io.bz.data.core.TdUpdateHandler
import io.bz.data.core.TdUpdatesProcessor
import io.bz.data.handlers.AuthUpdateHandler
import io.bz.data.handlers.ChatUpdateHandler
import io.bz.data.handlers.FileUpdateHandler
import io.bz.data.handlers.UsersUpdateHandler
import io.bz.data.lib.TdClientManager
import io.bz.data.mapper.auth.AuthStateMapper
import io.bz.data.mapper.chat.ChatMapper
import io.bz.data.mapper.user.UserDtoMapper
import io.bz.data.repostiory.AuthRepositoryImpl
import io.bz.data.repostiory.ChatRepositoryImpl
import io.bz.data.repostiory.FileRepositoryImpl
import io.bz.data.repostiory.UserRepositoryImpl
import io.bz.domain.repository.AuthRepository
import io.bz.domain.repository.ChatRepository
import io.bz.domain.repository.FileRepository
import io.bz.domain.repository.UserRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<TdUpdateHandler>(named("auth")) { AuthUpdateHandler(get(), get()) }
    single<TdUpdateHandler>(named("chat")) { ChatUpdateHandler(get(), get()) }
    single<TdUpdateHandler>(named("users")) { UsersUpdateHandler(get(), get()) }
    single<TdUpdateHandler>(named("file")) { FileUpdateHandler(get()) }

    single<TdUpdatesProcessor>(createdAtStart = true) {
        TdUpdatesProcessor(
            handlers = listOf(
                get(named("auth")),
                get(named("chat")),
                get(named("users")),
                get(named("file")),
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
            clientManager = get(),
            store = get(),
        )
    }

    single<FileRepository> {
        FileRepositoryImpl(
            clientManager = get(),
            store = get(),
        )
    }

    single<UserRepository> {
        UserRepositoryImpl(
            tdClientManager = get(),
            userStore = get(),
        )
    }


    factory { UserDtoMapper() }
    factory { AuthStateMapper() }
    factory { ChatMapper() }
}