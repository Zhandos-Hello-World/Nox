package io.bz.domain.interactors.file

sealed interface FileIntent {
    data class Load(
        val fileId: Int,
        val priority: Int,
        val offset: Long,
        val limit: Long,
        val synchronous: Boolean,
    ) : FileIntent
}