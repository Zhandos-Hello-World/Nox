package io.bz.data.stores

import io.bz.domain.model.user.User
import io.bz.domain.stores.UserStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserStoreImpl() : UserStore {
    private val _state = MutableStateFlow<List<User>>(emptyList())
    override val state: StateFlow<List<User>> = _state.asStateFlow()

    override suspend fun updateUser(user: User) {
        _state.emit(_state.value + user)
    }
}