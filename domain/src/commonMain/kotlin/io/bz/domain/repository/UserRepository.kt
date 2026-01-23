package io.bz.domain.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.user.User
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    val state: StateFlow<List<User>>
    suspend fun getContacts(): DomainResult<Unit>
}