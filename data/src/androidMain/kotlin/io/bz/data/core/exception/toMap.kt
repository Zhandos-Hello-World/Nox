package io.bz.data.core.exception

import io.bz.data.lib.TdApi
import io.bz.domain.core.DomainError
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException


fun Throwable.toMap(): DomainError = when (this) {
    is CancellationException -> throw this
    is TdLibException -> mapTdLibError(error)
    is IOException -> DomainError.NetworkError
    else -> DomainError.Unknown(message)
}

private fun mapTdLibError(error: TdApi.Error): DomainError = when (error.code) {
    401 -> DomainError.Unauthorized
    403 -> DomainError.Unauthorized
    429 -> DomainError.NetworkError // FLOOD_WAIT
    in 500..599 -> DomainError.NetworkError
    else -> DomainError.Unknown(error.message)
}

fun TdApi.Error.toException(): TdLibException = TdLibException(this)

