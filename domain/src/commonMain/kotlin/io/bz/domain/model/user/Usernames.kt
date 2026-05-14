package io.bz.domain.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usernames(
    @SerialName("active_usernames")
    val activeUsernames: List<String>,
    @SerialName("disabled_usernames")
    val disabledUsernames: List<String>,
    @SerialName("editable_username")
    val editableUsername: String? = null,
    @SerialName("collectible_usernames")
    val collectibleUsernames: List<String>
)