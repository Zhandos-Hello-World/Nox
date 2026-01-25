package io.bz.nox.di

import io.bz.data.di.commonDataModule
import io.bz.data.di.dataModule
import io.bz.nox.features.auth.presentation.authPresentationModule
import io.bz.nox.features.chats.presentation.list.chatsPresentationModule
import io.bz.nox.features.settings.presentation.settingsPresentationModule
import io.bz.nox.features.users.presentation.usersPresentationModule
import org.koin.core.context.startKoin

actual fun initModules() {
    startKoin {
        modules(
            appModule,
            dataModule,
            commonDataModule,
            authPresentationModule,
            settingsPresentationModule,
            usersPresentationModule,
            chatsPresentationModule,
        )
    }

}