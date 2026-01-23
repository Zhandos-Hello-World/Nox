package io.bz.data.handlers

import io.bz.data.core.TdNativeObjectWrapper
import io.bz.data.core.TdUpdateHandler
import org.drinkless.tdlib.TdApi
import io.bz.data.mapper.user.UserDtoMapper
import io.bz.domain.stores.UserStore

class UsersUpdateHandler(
    private val userStore: UserStore,
    private val mapper: UserDtoMapper,
) : TdUpdateHandler {

    override suspend fun handle(wrapper: TdNativeObjectWrapper): Boolean {
        when (val state = wrapper.tdApi) {
            is TdApi.UpdateUser -> {
                val userModel = mapper.map(state.user)
                userStore.updateUser(userModel)
            }
            else -> return false
        }
        return true
    }
}