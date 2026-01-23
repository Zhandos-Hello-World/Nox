package io.bz.data.interactors

import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.file.FileIntent
import io.bz.domain.interactors.file.FileService
import io.bz.domain.model.File
import io.bz.domain.repository.FileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FileServiceImpl(
    private val repository: FileRepository,
    coroutineScope: CoroutineScope,
) : FileService {
    override val files: StateFlow<List<File>> = repository.files.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    override suspend fun sendIntent(intent: FileIntent): DomainResult<Unit> {
        return when (intent) {
            is FileIntent.Load -> load(intent)
        }
    }

    private suspend fun load(intent: FileIntent.Load): DomainResult<Unit> {
        return repository.donwloadFile(
            fileId = intent.fileId,
            priority = intent.priority,
            offset = intent.offset,
            limit = intent.limit,
            synchronous = intent.synchronous,
        )
    }
}