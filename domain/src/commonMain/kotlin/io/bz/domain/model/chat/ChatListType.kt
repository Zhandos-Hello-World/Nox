package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator
@Serializable
@JsonClassDiscriminator("@type")
sealed interface ChatListType {

    @Serializable
    @SerialName("chatListMain")
    data object Main : ChatListType

    @Serializable
    @SerialName("chatListArchive")
    data object Archive : ChatListType

    @Serializable
    @SerialName("chatListFolder")
    data class Folder(
        @SerialName("chat_folder_id") val chatFolderId: Int
    ) : ChatListType
}