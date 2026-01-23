package io.bz.domain.stores

import io.bz.domain.model.File
import kotlinx.coroutines.flow.StateFlow

interface FileStore {
    val files: StateFlow<List<File>>

    suspend fun onNewFile(file: File)
}