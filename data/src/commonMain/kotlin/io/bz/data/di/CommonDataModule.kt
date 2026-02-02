package io.bz.data.di

import io.bz.data.interactors.AuthServiceImpl
import io.bz.data.interactors.ChatServiceImpl
import io.bz.data.interactors.FileServiceImpl
import io.bz.data.interactors.UserServiceImpl
import io.bz.data.stores.AuthStoreImpl
import io.bz.data.stores.ChatStoreImpl
import io.bz.data.stores.FileStoreImpl
import io.bz.data.stores.UserStoreImpl
import io.bz.domain.interactors.auth.AuthService
import io.bz.domain.interactors.chat.ChatService
import io.bz.domain.interactors.file.FileService
import io.bz.domain.interactors.user.UserService
import io.bz.domain.stores.AuthStore
import io.bz.domain.stores.ChatStore
import io.bz.domain.stores.FileStore
import io.bz.domain.stores.UserStore
import org.koin.dsl.module

val commonDataModule = module {

    single<AuthStore> { AuthStoreImpl() }
    single<ChatStore> { ChatStoreImpl() }
    single<UserStore> { UserStoreImpl() }
    single<FileStore> { FileStoreImpl() }

    single<AuthService> {
        AuthServiceImpl(
            repository = get(),
        )
    }

    single<ChatService> {
        ChatServiceImpl(
            repository = get(),
            fileRepository = get(),
            coroutineScope = get(),
        )
    }

    single<UserService> {
        UserServiceImpl(
            repository = get(),
            fileRepository = get(),
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