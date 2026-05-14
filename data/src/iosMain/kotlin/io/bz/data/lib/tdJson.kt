package io.bz.data.lib

import kotlinx.serialization.json.Json

val tdJson = Json {
    ignoreUnknownKeys = true // Игнорируем поля, которые нам пока не важны
    classDiscriminator = "@type" // TDLib использует это поле для определения типа
}