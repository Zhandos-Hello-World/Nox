package io.bz.domain.model.chat

import io.bz.domain.model.FormattedText

data class UpgradedGiftOriginalDetails(
    val senderId: Message.MessageSender?,
    val receiverId: Message.MessageSender,
    val text: FormattedText,
    val date: Int
)

data class GiftResaleParameters(
    val starCount: Long,
    val toncoinCentCount: Long,
    val toncoinOnly: Boolean
)
