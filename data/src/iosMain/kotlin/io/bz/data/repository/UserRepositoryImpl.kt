package io.bz.data.repository

import io.bz.data.lib.TdClientManager
import io.bz.domain.core.DomainResult
import io.bz.domain.model.user.User
import io.bz.domain.repository.UserRepository
import io.bz.domain.stores.UserStore
import kotlinx.coroutines.flow.StateFlow

class UserRepositoryImpl(
    userStore: UserStore,
    private val clientManager: TdClientManager,
): UserRepository {
    override val state: StateFlow<List<User>> = userStore.state

    override suspend fun getContacts(): DomainResult<Unit> {
        val jsonQuery = """{"@type":"getContacts"}"""
        clientManager.send(jsonQuery)
        return DomainResult.Success(Unit)
    }

}