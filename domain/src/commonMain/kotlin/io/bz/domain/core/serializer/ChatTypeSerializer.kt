package io.bz.domain.core.serializer

import io.bz.domain.model.chat.ChatType
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object ChatTypeSerializer : JsonContentPolymorphicSerializer<ChatType>(ChatType::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<ChatType> {
        val type = element.jsonObject["@type"]?.jsonPrimitive?.content
        return when (type) {
            "chatTypePrivate" -> ChatType.Private.serializer()
            "chatTypeBasicGroup" -> ChatType.BasicGroup.serializer()
            "chatTypeSupergroup" -> ChatType.Supergroup.serializer()
            "chatTypeSecret" -> ChatType.Secret.serializer()
            else -> ChatType.BasicGroup.serializer()
        }
    }
}