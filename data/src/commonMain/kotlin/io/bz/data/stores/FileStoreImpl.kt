package io.bz.data.stores

import io.bz.domain.model.File
import io.bz.domain.stores.FileStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FileStoreImpl() : FileStore {
    private val _files = MutableStateFlow<List<File>>(emptyList())
    override val files: StateFlow<List<File>> = _files.asStateFlow()

    override suspend fun onNewFile(file: File) {
        _files.emit(_files.value + file)
    }
}