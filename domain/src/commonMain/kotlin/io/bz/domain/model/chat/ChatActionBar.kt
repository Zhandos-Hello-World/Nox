package io.bz.domain.model.chat

sealed class ChatActionBar {

    object ReportSpam : ChatActionBar()

    object AddContact : ChatActionBar()

    object SharePhoneNumber : ChatActionBar()

    data class JoinRequest(
        val title: String,
        val isChannel: Boolean,
        val requestDate: Int
    ) : ChatActionBar()

    data object InviteMembers : ChatActionBar()

    data class ReportAddBlock(
        val canUnarchive: Boolean
    ) : ChatActionBar()
}