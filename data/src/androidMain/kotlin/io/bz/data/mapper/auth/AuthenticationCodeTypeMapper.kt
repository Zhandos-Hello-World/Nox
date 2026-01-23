package io.bz.data.mapper.auth

import org.drinkless.tdlib.TdApi
import io.bz.domain.state.AuthState

fun TdApi.AuthenticationCodeType.toDomain(): AuthState.WaitCode.AuthenticationCodeType =
    when (this.getConstructor()) {
        TdApi.AuthenticationCodeTypeTelegramMessage.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.TelegramMessage
        TdApi.AuthenticationCodeTypeSms.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.Sms
        TdApi.AuthenticationCodeTypeSmsWord.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.SmsWord
        TdApi.AuthenticationCodeTypeSmsPhrase.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.SmsPhrase
        TdApi.AuthenticationCodeTypeCall.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.Call
        TdApi.AuthenticationCodeTypeFlashCall.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.FlashCall
        TdApi.AuthenticationCodeTypeMissedCall.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.MissedCall
        TdApi.AuthenticationCodeTypeFragment.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.Fragment
        TdApi.AuthenticationCodeTypeFirebaseAndroid.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.FirebaseAndroid
        TdApi.AuthenticationCodeTypeFirebaseIos.CONSTRUCTOR ->
            AuthState.WaitCode.AuthenticationCodeType.FirebaseIos
        else -> error("Unknown AuthenticationCodeType constructor: ${this.getConstructor()}")
    }
