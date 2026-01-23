package io.bz.data.interactors

import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.user.UserIntent
import io.bz.domain.interactors.user.UserService
import io.bz.domain.model.user.User
import io.bz.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserServiceImpl(
    private val repository: UserRepository,
    coroutineScope: CoroutineScope,
) : UserService {
    override val state: StateFlow<Map<Long, User>> = repository.state.map {
        it.associateBy { it.id }
    }.stateIn(
        coroutineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = linkedMapOf()
    )

    override suspend fun sendIntent(intent: UserIntent): DomainResult<Unit> {
        return when (intent) {
            is UserIntent.GetUsers -> repository.getContacts()
            else -> TODO()
        }
    }

}