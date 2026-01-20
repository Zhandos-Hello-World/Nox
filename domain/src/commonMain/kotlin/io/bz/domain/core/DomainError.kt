package io.bz.domain.core

sealed class DomainError {
    object NetworkError : DomainError()
    object Unauthorized : DomainError()
    data class Unknown(val message: String? = null) : DomainError()
}