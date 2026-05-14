package io.bz.domain.model.chat

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
data class SuggestedPostInfo(
    @SerialName("price")
    val price: SuggestedPostPrice? = null,
    @SerialName("send_date")
    val sendDate: Int,
    @SerialName("state")
    val state: SuggestedPostState,
    @SerialName("can_be_approved")
    val canBeApproved: Boolean,
    @SerialName("can_be_declined")
    val canBeDeclined: Boolean
) {

    @Serializable
    @JsonClassDiscriminator("@type")
    sealed interface SuggestedPostPrice {

        @Serializable
        @SerialName("suggestedPostPriceStars")
        data class Stars(@SerialName("star_count") val starCount: Long) : SuggestedPostPrice

        @Serializable
        @SerialName("suggestedPostPriceTon")
        data class Ton(@SerialName("toncoin_cent_count") val toncoinCentCount: Long) : SuggestedPostPrice
    }

    @Serializable
    @JsonClassDiscriminator("@type")
    sealed interface SuggestedPostState {
        @Serializable @SerialName("suggestedPostStatePending") object Pending : SuggestedPostState
        @Serializable @SerialName("suggestedPostStateApproved") object Approved : SuggestedPostState
        @Serializable @SerialName("suggestedPostStateDeclined") object Declined : SuggestedPostState
    }
}