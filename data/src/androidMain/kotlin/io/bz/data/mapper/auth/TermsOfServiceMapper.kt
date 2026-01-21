package io.bz.data.mapper.auth

import io.bz.data.lib.TdApi
import io.bz.domain.model.FormattedText
import io.bz.domain.model.TextEntity
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
                type = it.type.toString(),
            )
        }
    )
}