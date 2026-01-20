package io.bz.domain.model.user

data class Usernames(
    val activeUsernames: List<String>,
    val disabledUsernames: List<String>,
    val editableUsername: String?,
    val collectibleUsernames: List<String>
)