package io.bz.data.repostiory

import io.bz.data.lib.TdClientManager
import io.bz.data.core.tdLibUnitCall
import org.drinkless.tdlib.TdApi
import io.bz.domain.core.DomainResult
import io.bz.domain.model.File
import io.bz.domain.repository.FileRepository
import io.bz.domain.stores.FileStore
import kotlinx.coroutines.flow.Flow


class FileRepositoryImpl(
    val clientManager: TdClientManager,
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
        return tdLibUnitCall(
            client = clientManager.client,
            request = TdApi.DownloadFile(
                fileId,
                priority,
                offset,
                limit,
                synchronous,
            ),
        )
    }
}