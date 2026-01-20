package io.bz.domain.model.chat

enum class ChatPermission {
    SEND_BASIC_MESSAGES,
    SEND_AUDIOS,
    SEND_DOCUMENTS,
    SEND_PHOTOS,
    SEND_VIDEOS,
    SEND_VIDEO_NOTES,
    SEND_VOICE_NOTES,
    SEND_POLLS,
    SEND_OTHER_MESSAGES,
    ADD_LINK_PREVIEWS,
    CHANGE_INFO,
    INVITE_USERS,
    PIN_MESSAGES,
    CREATE_TOPICS
}

data class ChatPermissions(
    val permissions: MutableSet<ChatPermission>,
)