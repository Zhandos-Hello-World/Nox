package io.bz.domain.model.user

sealed interface ActiveStoryState {

    data class Live(
        val storyId: Int
    ) : ActiveStoryState

    data object Unread : ActiveStoryState

    data object Read : ActiveStoryState
}
