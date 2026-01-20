package io.bz.domain.interactors.file

import io.bz.domain.state.FileState
import kotlinx.coroutines.flow.StateFlow

interface FileService {
    val files: StateFlow<FileState>

    suspend fun sendIntent(intent: FileIntent)
}