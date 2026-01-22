package io.bz.data.repostiory

import io.bz.data.lib.TdClientManager
import io.bz.data.core.tdLibUnitCall
import io.bz.data.lib.TdApi
import io.bz.domain.core.DomainResult
import io.bz.domain.repository.FileRepository


class FileRepositoryImpl(
    val clientManager: TdClientManager,
) : FileRepository {

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