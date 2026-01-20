package io.bz.domain.core

import io.bz.domain.lib.Client
import io.bz.domain.lib.TdApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class TdLibException(val error: TdApi.Error) :
    Exception("TDLib error ${error.code}: ${error.message}")

fun TdApi.Error.toException(): TdLibException = TdLibException(this)


suspend fun <T : TdApi.Object> tdLibCall(
    client: Client,
    request: TdApi.Function<T>,
): DomainResult<T> {
    return try {
        val result = suspendCancellableCoroutine<T> { continuation ->
            client.send(
                request,
                { res ->
                    when (res) {
                        is TdApi.Error -> continuation.resumeWith(
                            Result.failure(res.toException())
                        )

                        else -> continuation.resumeWith(
                            Result.success(res as T)
                        )
                    }
                },
                { error ->
                    continuation.resumeWith(Result.failure(error))
                },
            )
        }

        DomainResult.Success(result)
    } catch (ex: Throwable) {
        DomainResult.Failure(ex.toMap())
    }
}


suspend fun <T : TdApi.Object> tdLibUnitCall(
    client: Client,
    request: TdApi.Function<T>,
): DomainResult<Unit> {
    try {
        tdLibCall(client, request)
        return DomainResult.Success<Unit>(Unit)
    } catch (e: Throwable) {
        return DomainResult.Failure(e.toMap())
    }
}

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
