package io.bz.domain.core.serializer

import io.bz.domain.model.chat.ChatPermission
import io.bz.domain.model.chat.ChatPermissions
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

object ChatPermissionsSerializer : KSerializer<ChatPermissions> {
    
    // Генерируем дескриптор динамически на основе твоего Enum
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ChatPermissions") {
        ChatPermission.entries.forEach { permission ->
            // Извлекаем имя из @SerialName или используем lowercase имя
            val serialName = getSerialName(permission)
            element<Boolean>(serialName, isOptional = true)
        }
    }

    override fun deserialize(decoder: Decoder): ChatPermissions {
        val input = decoder as? JsonDecoder ?: throw SerializationException("Expected JSON for ChatPermissions")
        val jsonObject = input.decodeJsonElement().jsonObject
        
        val resultSet = mutableSetOf<ChatPermission>()
        
        // Проходим по всем возможным правам из твоего Enum
        ChatPermission.entries.forEach { permission ->
            val key = getSerialName(permission)
            if (jsonObject[key]?.jsonPrimitive?.booleanOrNull == true) {
                resultSet.add(permission)
            }
        }
        
        return ChatPermissions(resultSet)
    }

    override fun serialize(encoder: Encoder, value: ChatPermissions) {
        val output = encoder as? JsonEncoder ?: throw SerializationException("Expected JSON")
        
        // Создаем JSON объект, где для каждого активного разрешения пишем true
        val jsonMap = buildMap {
            ChatPermission.entries.forEach { permission ->
                val key = getSerialName(permission)
                // Telegram ожидает true для разрешенных и false для запрещенных
                put(key, JsonPrimitive(value.permissions.contains(permission)))
            }
        }
        
        output.encodeJsonElement(JsonObject(jsonMap))
    }

    // Вспомогательная функция, чтобы вытащить имя из @SerialName
    private fun getSerialName(permission: ChatPermission): String {
        return when (permission) {
            ChatPermission.SEND_BASIC_MESSAGES -> "can_send_basic_messages"
            ChatPermission.SEND_AUDIOS -> "can_send_audios"
            ChatPermission.SEND_DOCUMENTS -> "can_send_documents"
            ChatPermission.SEND_PHOTOS -> "can_send_photos"
            ChatPermission.SEND_VIDEOS -> "can_send_videos"
            ChatPermission.SEND_VIDEO_NOTES -> "can_send_video_notes"
            ChatPermission.SEND_VOICE_NOTES -> "can_send_voice_notes"
            ChatPermission.SEND_POLLS -> "can_send_polls"
            ChatPermission.SEND_OTHER_MESSAGES -> "can_send_other_messages"
            ChatPermission.SEND_LINK_PREVIEWS -> "can_add_link_previews"
            ChatPermission.CHANGE_INFO -> "can_change_info"
            ChatPermission.INVITE_USERS -> "can_invite_users"
            ChatPermission.PIN_MESSAGES -> "can_pin_messages"
            ChatPermission.CREATE_TOPICS -> "can_create_topics"
        }
    }
}