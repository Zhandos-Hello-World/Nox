package io.bz.data.mapper.chat

import org.drinkless.tdlib.TdApi
import io.bz.data.mapper.auth.toDomain
import io.bz.domain.model.chat.GiftResaleParameters
import io.bz.domain.model.chat.UpgradedGiftOriginalDetails
import io.bz.domain.model.chat.UpgradedGiftBackdrop
import io.bz.domain.model.chat.UpgradedGiftBackdropColors
import io.bz.domain.model.chat.BuiltInTheme
import io.bz.domain.model.chat.ChatTheme
import io.bz.domain.model.chat.GiftChatTheme
import io.bz.domain.model.chat.Sticker
import io.bz.domain.model.chat.StickerFormat
import io.bz.domain.model.chat.StickerFullType
import io.bz.domain.model.chat.ThemeSettings
import io.bz.domain.model.chat.UpgradedGift
import io.bz.domain.model.chat.UpgradedGiftModel
import io.bz.domain.model.chat.UpgradedGiftSymbol

fun TdApi.ChatTheme.toDomain(): ChatTheme = when (this) {
    is TdApi.ChatThemeEmoji -> ChatTheme.Emoji(name)
    is TdApi.ChatThemeGift -> ChatTheme.Gift(giftTheme.toDomain())
    else -> error("Unknown ChatTheme: ${this::class.java}")
}

fun TdApi.GiftChatTheme.toDomain(): GiftChatTheme =
    GiftChatTheme(
        gift = gift.toDomain(),
        lightSettings = lightSettings.toDomain(),
        darkSettings = darkSettings.toDomain()
    )

fun TdApi.UpgradedGift.toDomain(): UpgradedGift =
    UpgradedGift(
        id = id,
        regularGiftId = regularGiftId,
        publisherChatId = publisherChatId,
        title = title,
        name = name,
        number = number,
        totalUpgradedCount = totalUpgradedCount,
        maxUpgradedCount = maxUpgradedCount,
        isPremium = isPremium,
        isThemeAvailable = isThemeAvailable,
        usedThemeChatId = usedThemeChatId,
        hostId = hostId?.toDomain(),
        ownerId = ownerId?.toDomain(),
        ownerAddress = ownerAddress,
        ownerName = ownerName,
        giftAddress = giftAddress,
        model = model.toDomain(),
        symbol = symbol.toDomain(),
        backdrop = backdrop.toDomain(),
        originalDetails = originalDetails?.toDomain(),
        colors = colors?.toDomain(),
        resaleParameters = resaleParameters?.toDomain(),
        valueCurrency = valueCurrency,
        valueAmount = valueAmount,
    )

fun TdApi.UpgradedGiftModel.toDomain(): UpgradedGiftModel =
    UpgradedGiftModel(name, sticker.toDomain(), rarityPerMille)

fun TdApi.UpgradedGiftSymbol.toDomain(): UpgradedGiftSymbol =
    UpgradedGiftSymbol(name, sticker.toDomain(), rarityPerMille)

fun TdApi.Sticker.toDomain(): Sticker =
    Sticker(
        id = id,
        setId = setId,
        width = width,
        height = height,
        emoji = emoji,
        format = format.toDomain(),
        fullType = fullType.toDomain(),
        thumbnail = thumbnail?.toDomain(),
        sticker = sticker.toDomain()
    )

fun TdApi.StickerFormat.toDomain(): StickerFormat = when (this) {
    is TdApi.StickerFormatWebp -> StickerFormat.Webp
    is TdApi.StickerFormatTgs -> StickerFormat.Tgs
    is TdApi.StickerFormatWebm -> StickerFormat.Webm
    else -> error("Unknown StickerFormat: ${this::class.java}")
}

fun TdApi.BuiltInTheme.toDomain(): BuiltInTheme = when (this) {
    is TdApi.BuiltInThemeClassic -> BuiltInTheme.Classic
    is TdApi.BuiltInThemeDay -> BuiltInTheme.Day
    is TdApi.BuiltInThemeNight -> BuiltInTheme.Night
    is TdApi.BuiltInThemeTinted -> BuiltInTheme.Tinted
    is TdApi.BuiltInThemeArctic -> BuiltInTheme.Arctic
    else -> error("Unknown BuiltInTheme: ${this::class.java}")
}

fun TdApi.ThemeSettings.toDomain(): ThemeSettings =
    ThemeSettings(
        baseTheme = baseTheme.toDomain(),
        accentColor = accentColor,
        background = background?.toDomain(),
        outgoingMessageFill = outgoingMessageFill?.toDomain(),
        animateOutgoingMessageFill = animateOutgoingMessageFill,
        outgoingMessageAccentColor = outgoingMessageAccentColor
    )


fun TdApi.UpgradedGiftBackdrop.toDomain(): UpgradedGiftBackdrop =
    UpgradedGiftBackdrop(
        id = id,
        name = name,
        colors = colors.toDomain2(),
        rarityPerMille = rarityPerMille
    )


fun TdApi.UpgradedGiftBackdropColors.toDomain2(): UpgradedGiftBackdropColors =
    UpgradedGiftBackdropColors(
        centerColor = centerColor,
        edgeColor = edgeColor,
        symbolColor = symbolColor,
        textColor = textColor
    )



fun TdApi.UpgradedGiftOriginalDetails.toDomain(): UpgradedGiftOriginalDetails =
    UpgradedGiftOriginalDetails(
        senderId = senderId?.toDomain(),
        receiverId = receiverId.toDomain(),
        text = text.toDomain(),
        date = date
    )

fun TdApi.GiftResaleParameters.toDomain(): GiftResaleParameters =
    GiftResaleParameters(
        starCount = starCount,
        toncoinCentCount = toncoinCentCount,
        toncoinOnly = toncoinOnly
    )

fun TdApi.StickerFullType.toDomain(): StickerFullType = when (this) {
    is TdApi.StickerFullTypeRegular ->
        StickerFullType.Regular

    is TdApi.StickerFullTypeMask ->
        StickerFullType.Mask(
            maskPosition = maskPosition?.toDomain()
        )

    is TdApi.StickerFullTypeCustomEmoji ->
        StickerFullType.CustomEmoji(
            customEmojiId = customEmojiId,
            needsRepainting = needsRepainting
        )

    else -> error("Unknown StickerFullType: ${this::class.java}")
}

fun TdApi.MaskPosition.toDomain(): StickerFullType.Mask.MaskPosition =
    StickerFullType.Mask.MaskPosition(
        point = point.toDomain(),
        xShift = xShift,
        yShift = yShift,
        scale = scale
    )


fun TdApi.MaskPoint.toDomain(): StickerFullType.Mask.MaskPoint = when (this) {
    is TdApi.MaskPointForehead -> StickerFullType.Mask.MaskPoint.Forehead
    is TdApi.MaskPointEyes -> StickerFullType.Mask.MaskPoint.Eyes
    is TdApi.MaskPointMouth -> StickerFullType.Mask.MaskPoint.Mouth
    is TdApi.MaskPointChin -> StickerFullType.Mask.MaskPoint.Chin
    else -> error("Unknown MaskPoint: ${this::class.java}")
}




