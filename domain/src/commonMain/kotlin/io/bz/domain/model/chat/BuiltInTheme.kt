package io.bz.domain.model.chat

sealed interface BuiltInTheme {
    object Classic : BuiltInTheme
    object Day : BuiltInTheme
    object Night : BuiltInTheme
    object Tinted : BuiltInTheme
    object Arctic : BuiltInTheme
}
