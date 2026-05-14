package io.bz.domain.model.user

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
sealed interface UserType {

    @Serializable
    @SerialName("userTypeRegular")
    data object Regular : UserType

    @Serializable
    @SerialName("userTypeDeleted")
    data object Deleted : UserType

    @Serializable
    @SerialName("userTypeUnknown")
    data object Unknown : UserType

    @Serializable
    @SerialName("userTypeBot")
    data class Bot(
        @SerialName("can_be_edited")
        val canBeEdited: Boolean,
        @SerialName("can_join_groups")
        val canJoinGroups: Boolean,
        @SerialName("can_read_all_group_messages")
        val canReadAllGroupMessages: Boolean,
        @SerialName("has_main_web_app")
        val hasMainWebApp: Boolean,
        @SerialName("has_topics")
        val hasTopics: Boolean,
        @SerialName("is_inline")
        val isInline: Boolean,
        @SerialName("inline_query_placeholder")
        val inlineQueryPlaceholder: String? = null,
        @SerialName("need_location")
        val needLocation: Boolean,
        @SerialName("can_connect_to_business")
        val canConnectToBusiness: Boolean,
        @SerialName("can_be_added_to_attachment_menu")
        val canBeAddedToAttachmentMenu: Boolean,
        @SerialName("active_user_count")
        val activeUserCount: Int
    ) : UserType
}