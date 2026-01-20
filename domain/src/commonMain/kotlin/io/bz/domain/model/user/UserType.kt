package io.bz.domain.model.user

sealed interface UserType {

    data object Regular : UserType

    data object Deleted : UserType

    data object Unknown : UserType

    data class Bot(
        val canBeEdited: Boolean,
        val canJoinGroups: Boolean,
        val canReadAllGroupMessages: Boolean,
        val hasMainWebApp: Boolean,
        val hasTopics: Boolean,
        val isInline: Boolean,
        val inlineQueryPlaceholder: String?,
        val needLocation: Boolean,
        val canConnectToBusiness: Boolean,
        val canBeAddedToAttachmentMenu: Boolean,
        val activeUserCount: Int
    ) : UserType
}
