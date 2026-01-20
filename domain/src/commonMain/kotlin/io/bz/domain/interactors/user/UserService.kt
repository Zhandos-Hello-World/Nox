package io.bz.domain.interactors.user

import io.bz.domain.state.UserState
import kotlinx.coroutines.flow.StateFlow

interface UserService {
    val state: StateFlow<UserState>
    suspend fun sendIntent(intent: UserIntent)

}