package io.bz.data.mapper.auth

import org.drinkless.tdlib.TdApi
import io.bz.domain.model.FormattedText
import io.bz.domain.model.TextEntity
import io.bz.domain.model.TextEntityType
import io.bz.domain.state.AuthState

fun TdApi.TermsOfService.toDomain() : AuthState.WaitRegistration.TermsOfService {
    return AuthState.WaitRegistration.TermsOfService(
        text = this.text.toDomain(),
        minUserAge = this.minUserAge,
        showPopup = this.showPopup,
    )
}

fun TdApi.FormattedText.toDomain() : FormattedText {
    return FormattedText(
        text = this.text,
        entities = entities.map {
            TextEntity(
                offset = it.offset,
                length = it.length,
                type = it.type.toDomain()
            )
        }
    )
}

fun TdApi.TextEntityType.toDomain(): TextEntityType {
    return when (this) {
        is TdApi.TextEntityTypeBold -> TextEntityType.Bold
        is TdApi.TextEntityTypeItalic -> TextEntityType.Italic
        is TdApi.TextEntityTypeUnderline -> TextEntityType.Underline
        is TdApi.TextEntityTypeStrikethrough -> TextEntityType.Strikethrough
        is TdApi.TextEntityTypeCode -> TextEntityType.Code
        is TdApi.TextEntityTypePre -> TextEntityType.Pre
        is TdApi.TextEntityTypeSpoiler -> TextEntityType.Spoiler
        is TdApi.TextEntityTypeBlockQuote -> TextEntityType.BlockQuote
        is TdApi.TextEntityTypeMention -> TextEntityType.Mention
        is TdApi.TextEntityTypeUrl -> TextEntityType.Url
        is TdApi.TextEntityTypeTextUrl -> TextEntityType.TextUrl(url = this.url)
        is TdApi.TextEntityTypeMentionName -> TextEntityType.MentionName(userId = this.userId)
        is TdApi.TextEntityTypeCustomEmoji -> TextEntityType.CustomEmoji(customEmojiId = this.customEmojiId)
        else -> TextEntityType.Bold
    }
}