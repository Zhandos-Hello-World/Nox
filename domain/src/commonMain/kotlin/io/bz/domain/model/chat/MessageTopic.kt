package io.bz.domain.model.chat

sealed interface MessageTopic {

    data class Thread(
        val messageThreadId: Long
    ) : MessageTopic

    data class Forum(
        val forumTopicId: Int
    ) : MessageTopic

    data class DirectMessages(
        val directMessagesChatTopicId: Long
    ) : MessageTopic

    data class SavedMessages(
        val savedMessagesTopicId: Long
    ) : MessageTopic
}
