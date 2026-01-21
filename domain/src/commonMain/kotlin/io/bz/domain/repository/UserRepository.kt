package io.bz.domain.repository

import io.bz.domain.core.DomainResult

interface UserRepository {

    suspend fun getContacts(): DomainResult<Unit>
}