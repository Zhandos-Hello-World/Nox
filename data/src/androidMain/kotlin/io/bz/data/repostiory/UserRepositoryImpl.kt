package io.bz.data.repostiory

import io.bz.data.core.tdLibUnitCall
import org.drinkless.tdlib.TdApi
import io.bz.data.lib.TdClientManager
import io.bz.domain.core.DomainResult
import io.bz.domain.model.user.User
import io.bz.domain.repository.UserRepository
import io.bz.domain.stores.UserStore
import kotlinx.coroutines.flow.StateFlow

class UserRepositoryImpl(
    private val tdClientManager: TdClientManager,
    userStore: UserStore,
): UserRepository {
    override val state: StateFlow<List<User>> = userStore.state

    override suspend fun getContacts(): DomainResult<Unit> {
        return tdLibUnitCall(
            client = tdClientManager.client,
            request = TdApi.GetContacts(),
        )
    }

}