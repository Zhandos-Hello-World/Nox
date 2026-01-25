package io.bz.data.repository

import io.bz.domain.core.DomainResult
import io.bz.domain.model.File
import io.bz.domain.repository.FileRepository
import io.bz.domain.stores.FileStore
import kotlinx.coroutines.flow.Flow


class FileRepositoryImpl(
    val store: FileStore,
) : FileRepository {
    override val files: Flow<List<File>> = store.files

    override suspend fun donwloadFile(
        fileId: Int,
        priority: Int,
        offset: Long,
        limit: Long,
        synchronous: Boolean,
    ): DomainResult<Unit> {
        return DomainResult.Success(Unit)
    }
}