package io.bz.data.mapper.auth

import org.drinkless.tdlib.TdApi
import io.bz.domain.state.AuthenticationCodeType

fun TdApi.AuthenticationCodeType.toDomain(): AuthenticationCodeType =
    when (this.getConstructor()) {
        TdApi.AuthenticationCodeTypeTelegramMessage.CONSTRUCTOR ->
            AuthenticationCodeType.TelegramMessage
        TdApi.AuthenticationCodeTypeSms.CONSTRUCTOR ->
            AuthenticationCodeType.Sms
        TdApi.AuthenticationCodeTypeSmsWord.CONSTRUCTOR ->
            AuthenticationCodeType.Sms
        TdApi.AuthenticationCodeTypeSmsPhrase.CONSTRUCTOR ->
            AuthenticationCodeType.Sms
        TdApi.AuthenticationCodeTypeCall.CONSTRUCTOR ->
            AuthenticationCodeType.Call
        TdApi.AuthenticationCodeTypeFlashCall.CONSTRUCTOR ->
            AuthenticationCodeType.FlashCall
        TdApi.AuthenticationCodeTypeMissedCall.CONSTRUCTOR ->
            AuthenticationCodeType.MissedCall
        TdApi.AuthenticationCodeTypeFragment.CONSTRUCTOR ->
            AuthenticationCodeType.Fragment
        TdApi.AuthenticationCodeTypeFirebaseAndroid.CONSTRUCTOR ->
            AuthenticationCodeType.FirebaseAndroid
        TdApi.AuthenticationCodeTypeFirebaseIos.CONSTRUCTOR ->
            AuthenticationCodeType.FirebaseIos
        else -> error("Unknown AuthenticationCodeType constructor: ${this.getConstructor()}")
    }
