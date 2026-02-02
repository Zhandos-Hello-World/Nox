package io.bz.data.core

import io.bz.data.core.exception.toException
import io.bz.data.core.exception.toMap
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import io.bz.domain.core.DomainResult
import kotlinx.coroutines.suspendCancellableCoroutine


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

suspend fun <FROM, TO> DomainResult<FROM>.tdLibMapper(
    mapping: (DomainResult<FROM>) -> DomainResult<TO>,
): DomainResult<TO> {
    return mapping.invoke(this)
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
