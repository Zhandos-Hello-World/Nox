package io.bz.domain.interactors.file

import io.bz.domain.core.DomainResult
import io.bz.domain.model.File
import io.bz.domain.state.FileState
import kotlinx.coroutines.flow.StateFlow

interface FileService {
    val files: StateFlow<List<File>>

    suspend fun sendIntent(intent: FileIntent) : DomainResult<Unit>
}