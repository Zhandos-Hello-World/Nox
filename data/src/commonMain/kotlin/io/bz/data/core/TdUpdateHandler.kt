package io.bz.data.core

interface TdUpdateHandler {
    suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean
}