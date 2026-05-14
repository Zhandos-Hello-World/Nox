package io.bz.domain.model.user

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("@type")
sealed interface UserStatus {

    @Serializable
    @SerialName("userStatusEmpty")
    data object Empty : UserStatus

    @Serializable
    @SerialName("userStatusOnline")
    data class Online(
        @SerialName("expires")
        val expires: Int
    ) : UserStatus

    @Serializable
    @SerialName("userStatusOffline")
    data class Offline(
        @SerialName("was_online")
        val wasOnline: Int
    ) : UserStatus

    @Serializable
    @SerialName("userStatusRecently")
    data class Recently(
        @SerialName("by_my_privacy_settings")
        val byMyPrivacySettings: Boolean
    ) : UserStatus

    @Serializable
    @SerialName("userStatusLastWeek")
    data class LastWeek(
        @SerialName("by_my_privacy_settings")
        val byMyPrivacySettings: Boolean
    ) : UserStatus

    @Serializable
    @SerialName("userStatusLastMonth")
    data class LastMonth(
        @SerialName("by_my_privacy_settings")
        val byMyPrivacySettings: Boolean
    ) : UserStatus
}