package io.bz.nox.features.users.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bz.domain.interactors.file.FileIntent
import io.bz.domain.interactors.file.FileService
import io.bz.domain.interactors.user.UserIntent
import io.bz.domain.interactors.user.UserService
import io.bz.domain.model.user.ProfilePhoto
import io.bz.nox.core.log
import kotlinx.coroutines.launch

class UsersMainViewModel(
    private val userService: UserService,
    private val fileService: FileService,
) : ViewModel() {
    val state = userService.state

    fun loadAvatar(photo: ProfilePhoto) {
        val file = photo.small

        if (!file.local.isDownloadingCompleted) {
            log("UpdateFileRequest", file.id.toString())
            viewModelScope.launch {
                fileService.sendIntent(
                    FileIntent.Load(
                        fileId = file.id,
                        1,
                        0,
                        0,
                        false,
                    )
                )
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            userService.sendIntent(UserIntent.GetUsers)
        }
    }
}