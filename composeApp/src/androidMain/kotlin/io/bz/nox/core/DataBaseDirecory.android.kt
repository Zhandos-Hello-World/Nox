package io.bz.nox.core

import io.bz.nox.NoxApplication

actual fun dataBaseDirectory(): String {
    return NoxApplication.context.cacheDir.absolutePath
}