package io.bz.nox.features.settings.presentation

import io.bz.nox.features.settings.presentation.main.SettingsMainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsPresentationModule = module {

    viewModel {
        SettingsMainViewModel(
            authService = get(),
        )
    }

}
