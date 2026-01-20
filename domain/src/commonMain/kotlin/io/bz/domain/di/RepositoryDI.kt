package io.bz.domain.di

import io.bz.domain.repository.AuthRepository
import io.bz.domain.repository.ChatRepository

expect fun getAuthRepository(): AuthRepository

expect fun getChatRepository(): ChatRepository

