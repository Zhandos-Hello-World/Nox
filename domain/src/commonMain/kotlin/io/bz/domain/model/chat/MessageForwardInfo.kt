package io.bz.domain.model.chat

data class MessageForwardInfo(
        val origin: MessageOrigin,
        val date: Int,
        val source: ForwardSource?,
        val publicServiceAnnouncementType: String?
    ) {

        sealed interface MessageOrigin {
            data class User(
                val senderUserId: Long
            ) : MessageOrigin

            data class HiddenUser(
                val senderName: String
            ) : MessageOrigin

            data class FromChat(
                val senderChatId: Long,
                val authorSignature: String?
            ) : MessageOrigin

            data class Channel(
                val chatId: Long,
                val messageId: Long,
                val authorSignature: String?
            ) : MessageOrigin
        }

        data class ForwardSource(
            val chatId: Long?,
            val messageId: Long?,
            val sender: Message.MessageSender?,
            val senderName: String?,
            val date: Int?,
            val isOutgoing: Boolean
        )
    }