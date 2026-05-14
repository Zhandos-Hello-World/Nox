package io.bz.data.handlers

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import io.bz.domain.model.chat.ChatModel
import io.bz.domain.model.chat.Message
import io.bz.domain.state.AuthState
import io.bz.domain.stores.ChatStore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val jsonFormat = Json {
    ignoreUnknownKeys = true // Пропускать поля, которых нет в твоем ChatModel
    isLenient = true         // Игнорировать мелкие ошибки в формате JSON
    encodeDefaults = true    // Использовать дефолтные значения из data class
    coerceInputValues = true //
}
class ChatUpdateHandler(
    private val chatStore: ChatStore,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        val json = wrapper.jsonResponse ?: return false
        when {
            json.contains("updateNewChat") -> {
                val update = jsonFormat.decodeFromString<UpdateNewChat>(json)
                chatStore.onNewChat(update.chat)
                if (update.chat.lastMessage != null) {
                    println("handleGotZhasik: ${update.chat.lastMessage}")

                }
            }
            json.contains("updateChatLastMessage") -> {
                val update = jsonFormat.decodeFromString<UpdateChatLastMessage>(json)
                chatStore.updateLastMessage(
                    chatId = update.chatId,
                    lastMessage = update.lastMessage,
                )
            }
            //UpdateChatPosition, UpdateChatTitle
            else -> return false
        }
        return true
    }
}

@Serializable
@SerialName("updateNewChat")
data class UpdateNewChat(
    @SerialName("chat") val chat: ChatModel
)

@Serializable
@SerialName("updateChatLastMessage")
data class UpdateChatLastMessage(
    @SerialName("chat_id") val chatId: Long,
    @SerialName("last_message") val lastMessage: Message? = null // Обязательно ? и = null
)