package io.bz.domain.model

data class File(
    val id: Int,
    val size: Long,
    val expectedSize: Long,
    val local: LocalFile,
    val remote: RemoteFile,
) {

    data class LocalFile(
        val path: String,
        val canBeDownloaded: Boolean,
        val canBeDeleted: Boolean,
        val isDownloadingActive: Boolean,
        val isDownloadingCompleted: Boolean,
        val downloadOffset: Long,
        val downloadedPrefixSize: Long,
        val downloadedSize: Long,
    )

    data class RemoteFile(
        val id: String,
        val uniqueId: String,
        val isUploadingActive: Boolean,
        val isUploadingCompleted: Boolean,
        val uploadedSize: Long,
    )

}