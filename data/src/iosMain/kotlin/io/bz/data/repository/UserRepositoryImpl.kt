package io.bz.data.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.user.User
import io.bz.domain.repository.UserRepository
import io.bz.domain.stores.UserStore
import kotlinx.coroutines.flow.StateFlow

class UserRepositoryImpl(
    userStore: UserStore,
): UserRepository {
    override val state: StateFlow<List<User>> = userStore.state

    override suspend fun getContacts(): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }

}