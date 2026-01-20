package io.bz.domain.model.user

sealed interface UserStatus {
        data object Empty : UserStatus

        data class Online(
            val expires: Int
        ) : UserStatus

        data class Offline(
            val wasOnline: Int
        ) : UserStatus

        data class Recently(
            val byMyPrivacySettings: Boolean
        ) : UserStatus

        data class LastWeek(
            val byMyPrivacySettings: Boolean
        ) : UserStatus

        data class LastMonth(
            val byMyPrivacySettings: Boolean
        ) : UserStatus
    }