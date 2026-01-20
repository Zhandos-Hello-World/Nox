package io.bz.domain.model.chat

data class BusinessBotManageBar(
    val botUserId: Long,
    val manageUrl: String,
    val isBotPaused: Boolean
)
