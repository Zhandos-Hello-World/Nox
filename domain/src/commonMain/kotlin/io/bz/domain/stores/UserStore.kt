package io.bz.domain.stores

import io.bz.domain.model.user.User
import kotlinx.coroutines.flow.StateFlow

interface UserStore {
    val state: StateFlow<List<User>>

    suspend fun updateUser(user: User)
}