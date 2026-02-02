package io.bz.data.handlers

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import io.bz.data.mapper.chat.toDomain
import io.bz.domain.stores.FileStore
import org.drinkless.tdlib.TdApi

class FileUpdateHandler(
    private val fileStore: FileStore,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        when (val state = wrapper.tdApi) {
            is TdApi.UpdateFile -> {
                fileStore.onNewFile(state.file.toDomain())
            }
            else -> return false
        }
        return true
    }

}