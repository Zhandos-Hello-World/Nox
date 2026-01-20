package io.bz.domain.state

import io.bz.domain.model.user.User

sealed interface UserState {
    data class Data(
        val users: Map<Long, User>,
    ) : UserState

    data object None : UserState
}