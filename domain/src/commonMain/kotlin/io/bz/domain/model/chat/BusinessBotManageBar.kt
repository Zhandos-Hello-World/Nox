package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BusinessBotManageBar(
    @SerialName("bot_user_id")
    val botUserId: Long,

    @SerialName("manage_url")
    val manageUrl: String,

    @SerialName("is_bot_paused")
    val isBotPaused: Boolean
)