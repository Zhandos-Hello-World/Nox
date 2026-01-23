package io.bz.domain.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.File
import kotlinx.coroutines.flow.Flow

interface FileRepository {
    val files: Flow<List<File>>
    suspend fun donwloadFile(
        fileId: Int,
        priority: Int,
        offset: Long,
        limit: Long,
        synchronous: Boolean,
    ) : DomainResult<Unit>
}