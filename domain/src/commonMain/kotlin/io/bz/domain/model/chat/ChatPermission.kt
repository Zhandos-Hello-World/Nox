package io.bz.domain.model.chat

import io.bz.domain.core.serializer.ChatPermissionsSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChatPermission {
    @SerialName("can_send_basic_messages") SEND_BASIC_MESSAGES,
    @SerialName("can_send_audios") SEND_AUDIOS,
    @SerialName("can_send_documents") SEND_DOCUMENTS,
    @SerialName("can_send_photos") SEND_PHOTOS,
    @SerialName("can_send_videos") SEND_VIDEOS,
    @SerialName("can_send_video_notes") SEND_VIDEO_NOTES,
    @SerialName("can_send_voice_notes") SEND_VOICE_NOTES,
    @SerialName("can_send_polls") SEND_POLLS,
    @SerialName("can_send_other_messages") SEND_OTHER_MESSAGES,
    @SerialName("can_add_link_previews") SEND_LINK_PREVIEWS,
    @SerialName("can_change_info") CHANGE_INFO,
    @SerialName("can_invite_users") INVITE_USERS,
    @SerialName("can_pin_messages") PIN_MESSAGES,
    @SerialName("can_create_topics") CREATE_TOPICS
}



@Serializable(with = ChatPermissionsSerializer::class)
data class ChatPermissions(
    val permissions: Set<ChatPermission> = emptySet()
)