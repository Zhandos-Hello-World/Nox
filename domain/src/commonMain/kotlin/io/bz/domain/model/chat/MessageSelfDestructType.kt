package io.bz.domain.model.chat

sealed interface MessageSelfDestructType {

    data class Timer(
        val seconds: Int
    ) : MessageSelfDestructType

    object Immediately : MessageSelfDestructType
}
