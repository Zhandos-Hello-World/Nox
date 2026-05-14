package io.bz.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class File(
    @SerialName("id")
    val id: Int,
    @SerialName("size")
    val size: Long,
    @SerialName("expected_size")
    val expectedSize: Long,
    @SerialName("local")
    val local: LocalFile,
    @SerialName("remote")
    val remote: RemoteFile,
) {
    @Serializable
    data class LocalFile(
        @SerialName("path")
        val path: String,
        @SerialName("can_be_downloaded")
        val canBeDownloaded: Boolean,
        @SerialName("can_be_deleted")
        val canBeDeleted: Boolean,
        @SerialName("is_downloading_active")
        val isDownloadingActive: Boolean,
        @SerialName("is_downloading_completed")
        val isDownloadingCompleted: Boolean,
        @SerialName("download_offset")
        val downloadOffset: Long,
        @SerialName("downloaded_prefix_size")
        val downloadedPrefixSize: Long,
        @SerialName("downloaded_size")
        val downloadedSize: Long,
    )

    @Serializable
    data class RemoteFile(
        @SerialName("id")
        val id: String,
        @SerialName("unique_id")
        val uniqueId: String,
        @SerialName("is_uploading_active")
        val isUploadingActive: Boolean,
        @SerialName("is_uploading_completed")
        val isUploadingCompleted: Boolean,
        @SerialName("uploaded_size")
        val uploadedSize: Long,
    )
}