package io.bz.domain.model.chat

sealed interface ChatSource {

    object MtprotoProxy : ChatSource

    data class PublicServiceAnnouncement(
        val type: String,
        val text: String
    ) : ChatSource
}
