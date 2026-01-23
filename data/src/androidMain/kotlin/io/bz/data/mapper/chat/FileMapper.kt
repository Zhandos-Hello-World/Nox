package io.bz.data.mapper.chat

import org.drinkless.tdlib.TdApi
import io.bz.domain.model.File

fun TdApi.File.toDomain(): File =
    File(
        id = id,
        size = size,
        expectedSize = expectedSize,
        local = local.toDomain(),
        remote = remote.toDomain()
    )

fun TdApi.LocalFile.toDomain(): File.LocalFile =
    File.LocalFile(
        path = path,
        canBeDownloaded = canBeDownloaded,
        canBeDeleted = canBeDeleted,
        isDownloadingActive = isDownloadingActive,
        isDownloadingCompleted = isDownloadingCompleted,
        downloadOffset = downloadOffset,
        downloadedPrefixSize = downloadedPrefixSize,
        downloadedSize = downloadedSize
    )

fun TdApi.RemoteFile.toDomain(): File.RemoteFile =
    File.RemoteFile(
        id = id,
        uniqueId = uniqueId,
        isUploadingActive = isUploadingActive,
        isUploadingCompleted = isUploadingCompleted,
        uploadedSize = uploadedSize
    )
