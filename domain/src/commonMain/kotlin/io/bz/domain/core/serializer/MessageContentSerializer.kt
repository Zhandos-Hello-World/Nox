package io.bz.domain.core.serializer

import io.bz.domain.model.chat.MessageContent
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object MessageContentSerializer : JsonContentPolymorphicSerializer<MessageContent>(MessageContent::class) {
    override fun selectDeserializer(element: JsonElement) = when {
        "text" in element.jsonObject -> MessageContent.MessageText.serializer()
        element.jsonObject["@type"]?.jsonPrimitive?.content == "messageContactRegistered" -> 
            MessageContent.MessageContactRegistered.serializer()
        else -> MessageContent.UnSupportedContent.serializer()
    }
}