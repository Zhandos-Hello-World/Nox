package io.bz.domain.model.chat

data class UpgradedGift(
    val id: Long,
    val regularGiftId: Long,
    val publisherChatId: Long,
    val title: String,
    val name: String,
    val number: Int,
    val totalUpgradedCount: Int,
    val maxUpgradedCount: Int,
    val isPremium: Boolean,
    val isThemeAvailable: Boolean,
    val usedThemeChatId: Long,
    val hostId: Message.MessageSender?,
    val ownerId: Message.MessageSender?,
    val ownerAddress: String,
    val ownerName: String,
    val giftAddress: String,
    val model: UpgradedGiftModel,
    val symbol: UpgradedGiftSymbol,
    val backdrop: UpgradedGiftBackdrop,
    val originalDetails: UpgradedGiftOriginalDetails?,
    val colors: UpgradedGiftColors?,
    val resaleParameters: GiftResaleParameters?,
    val valueCurrency: String,
    val valueAmount: Long
)
