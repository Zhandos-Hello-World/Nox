package io.bz.domain.state

import io.bz.domain.model.File

sealed interface FileState {
    data class Data(
        val files: List<File>,
    ) : FileState

    data object None : FileState
}