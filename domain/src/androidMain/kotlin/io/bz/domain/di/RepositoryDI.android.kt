package io.bz.domain.di

import io.bz.domain.repository.AuthRepository
import io.bz.domain.repository.AuthRepositoryImpl
import io.bz.domain.repository.ChatRepository
import io.bz.domain.repository.ChatRepositoryImpl

actual fun getAuthRepository(): AuthRepository {
    return AuthRepositoryImpl()
}

actual fun getChatRepository(): ChatRepository {
    return ChatRepositoryImpl()
}