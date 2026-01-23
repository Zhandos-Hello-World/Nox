package io.bz.nox.features.users.presentation

import io.bz.nox.features.users.presentation.main.UsersMainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val usersPresentationModule = module {

    viewModel {
        UsersMainViewModel(
            userService = get(),
            fileService = get(),
        )
    }

}