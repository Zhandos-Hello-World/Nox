package io.bz.data.handlers

import android.util.Log
import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import org.drinkless.tdlib.TdApi
import io.bz.data.mapper.chat.ChatMapper
import io.bz.data.mapper.chat.toDomain
import io.bz.domain.stores.ChatStore

class ChatUpdateHandler(
    private val chatStore: ChatStore,
    private val mapper: ChatMapper,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        when (val state = wrapper.tdApi) {
            is TdApi.UpdateNewChat -> {
                Log.d("ChatUpdateHandler", "handle: ${wrapper.tdApi}")
                val chatModel = mapper.map(state.chat)
                chatModel!!.let { chatStore.onNewChat(chatModel) }
            }

            is TdApi.UpdateChatLastMessage -> {
                chatStore.updateLastMessage(
                    chatId = state.chatId,
                    lastMessage = state.lastMessage?.toDomain(),
                )
            }

            is TdApi.UpdateChatPosition -> {
                chatStore.updatePosition(
                    chatId = state.chatId,
                    position = state.position.toDomain(),
                )
            }

            is TdApi.UpdateChatTitle -> {
                chatStore.updateTitle(
                    chatId = state.chatId,
                    title = state.title,
                )
            }
            else -> return false
        }
        return true
    }
}