package io.bz.data.di

import io.bz.data.core.TdUpdateHandler
import io.bz.data.core.TdUpdatesProcessor
import io.bz.data.handlers.AuthUpdateHandler
import io.bz.data.handlers.ChatUpdateHandler
import io.bz.data.handlers.UsersUpdateHandler
import io.bz.data.interactors.AuthServiceImpl
import io.bz.data.interactors.ChatServiceImpl
import io.bz.data.interactors.FileServiceImpl
import io.bz.data.interactors.UserServiceImpl
import io.bz.data.lib.TdClientManager
import io.bz.data.mapper.auth.AuthStateMapper
import io.bz.data.mapper.chat.ChatMapper
import io.bz.data.mapper.user.UserDtoMapper
import io.bz.data.repostiory.AuthRepositoryImpl
import io.bz.data.repostiory.ChatRepositoryImpl
import io.bz.data.repostiory.FileRepositoryImpl
import io.bz.data.repostiory.UserRepositoryImpl
import io.bz.data.stores.AuthStoreImpl
import io.bz.data.stores.ChatStoreImpl
import io.bz.data.stores.FileStoreImpl
import io.bz.data.stores.UserStoreImpl
import io.bz.domain.interactors.auth.AuthService
import io.bz.domain.interactors.chat.ChatService
import io.bz.domain.interactors.file.FileService
import io.bz.domain.interactors.user.UserService
import io.bz.domain.repository.AuthRepository
import io.bz.domain.repository.ChatRepository
import io.bz.domain.repository.FileRepository
import io.bz.domain.repository.UserRepository
import io.bz.domain.stores.AuthStore
import io.bz.domain.stores.ChatStore
import io.bz.domain.stores.FileStore
import io.bz.domain.stores.UserStore
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single<TdUpdateHandler>(named("auth")) { AuthUpdateHandler(get(), get()) }
    single<TdUpdateHandler>(named("chat")) { ChatUpdateHandler(get(), get()) }
    single<TdUpdateHandler>(named("users")) { UsersUpdateHandler(get(), get()) }


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

    single<AuthStore> { AuthStoreImpl() }
    single<ChatStore> { ChatStoreImpl() }
    single<UserStore> { UserStoreImpl() }
    single<FileStore> { FileStoreImpl() }

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

    single<AuthService> {
        AuthServiceImpl(
            repository = get(),
        )
    }

    single<ChatService> {
        ChatServiceImpl(
            repository = get(),
            coroutineScope = get(),
        )
    }

    single<UserService> {
        UserServiceImpl(
            repository = get(),
            coroutineScope = get(),
        )
    }

    single<FileService> {
        FileServiceImpl(
            repository = get(),
            coroutineScope = get(),
        )
    }
}