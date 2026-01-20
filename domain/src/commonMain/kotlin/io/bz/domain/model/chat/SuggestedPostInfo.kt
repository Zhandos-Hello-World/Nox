package io.bz.domain.model.chat

data class SuggestedPostInfo(
    val price: SuggestedPostPrice?,      // null = non-paid
    val sendDate: Int,                   // unix seconds, 0 = not set
    val state: SuggestedPostState,
    val canBeApproved: Boolean,
    val canBeDeclined: Boolean
) {
    sealed interface SuggestedPostPrice {

        data class Stars(
            val starCount: Long
        ) : SuggestedPostPrice

        data class Ton(
            val toncoinCentCount: Long
        ) : SuggestedPostPrice
    }

    enum class SuggestedPostState {
        PENDING,
        APPROVED,
        DECLINED
    }


}
