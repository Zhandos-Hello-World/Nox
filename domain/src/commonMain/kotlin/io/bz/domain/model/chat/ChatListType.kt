package io.bz.domain.model.chat

sealed interface ChatListType {

    object Main : ChatListType

    object Archive : ChatListType

    data class Folder(
        val chatFolderId: Int
    ) : ChatListType
}
