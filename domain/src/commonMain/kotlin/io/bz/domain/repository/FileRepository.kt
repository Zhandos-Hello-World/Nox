package io.bz.domain.repository

import io.bz.domain.core.DomainResult

interface FileRepository {

    suspend fun donwloadFile(
        fileId: Int,
        priority: Int,
        offset: Long,
        limit: Long,
        synchronous: Boolean,
    ) : DomainResult<Unit>
}