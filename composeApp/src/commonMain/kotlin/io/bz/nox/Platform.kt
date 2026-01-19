package io.bz.nox

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform