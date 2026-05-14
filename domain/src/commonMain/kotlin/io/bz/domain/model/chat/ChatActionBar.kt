package io.bz.domain.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("@type")
sealed class ChatActionBar {

    @Serializable
    @SerialName("chatActionBarReportSpam")
    object ReportSpam : ChatActionBar()

    @Serializable
    @SerialName("chatActionBarAddContact")
    object AddContact : ChatActionBar()

    @Serializable
    @SerialName("chatActionBarSharePhoneNumber")
    object SharePhoneNumber : ChatActionBar()

    @Serializable
    @SerialName("chatActionBarJoinRequest")
    data class JoinRequest(
        @SerialName("title") val title: String,
        @SerialName("is_channel") val isChannel: Boolean,
        @SerialName("request_date") val requestDate: Int
    ) : ChatActionBar()

    @Serializable
    @SerialName("chatActionBarInviteMembers")
    object InviteMembers : ChatActionBar()

    @Serializable
    @SerialName("chatActionBarReportAddBlock")
    data class ReportAddBlock(
        @SerialName("can_unarchive") val canUnarchive: Boolean
    ) : ChatActionBar()
}