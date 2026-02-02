package io.bz.data.interactors

import io.bz.domain.core.DomainResult
import io.bz.domain.interactors.user.UserIntent
import io.bz.domain.interactors.user.UserService
import io.bz.domain.model.File
import io.bz.domain.model.user.ProfilePhoto
import io.bz.domain.model.user.User
import io.bz.domain.repository.FileRepository
import io.bz.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserServiceImpl(
    private val repository: UserRepository,
    private val fileRepository: FileRepository,
    private val coroutineScope: CoroutineScope,
) : UserService {

    override val state: StateFlow<LinkedHashMap<Long, User>> = combine(
        repository.state,
        fileRepository.files.map { it.associateBy { it.id } }
    ) { users, files ->
        val newUsers = LinkedHashMap<Long, User>()

        users.forEach {
            val profilePhoto = it.profilePhoto?.small
            val updatedFile = files[profilePhoto?.id]

            if (updatedFile != null) {
                newUsers[it.id] = it.copy(profilePhoto = it.profilePhoto?.copy(small = updatedFile))
            } else {
                newUsers[it.id] = it
            }

            if (profilePhoto != null && !profilePhoto.local.isDownloadingCompleted) {
                triggeredUserAvatar(profilePhoto)
            }

        }
        newUsers
    }.stateIn(
        coroutineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = linkedMapOf()
    )

    override suspend fun sendIntent(intent: UserIntent): DomainResult<Unit> {
        return when (intent) {
            is UserIntent.GetUsers -> repository.getContacts()
            else -> TODO()
        }
    }

    private fun triggeredUserAvatar(file: File) {
        coroutineScope.launch {
            val res = fileRepository.donwloadFile(
                fileId = file.id,
                priority = 1,
                offset = 0,
                limit = 0,
                synchronous = false,
            )
        }
    }

}