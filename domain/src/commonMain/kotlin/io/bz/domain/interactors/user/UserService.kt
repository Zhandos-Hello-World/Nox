package io.bz.domain.interactors.user

import io.bz.domain.core.DomainResult
import io.bz.domain.model.user.User
import kotlinx.coroutines.flow.StateFlow

interface UserService {
    val state: StateFlow<LinkedHashMap<Long, User>>
    suspend fun sendIntent(intent: UserIntent) : DomainResult<Unit>

}