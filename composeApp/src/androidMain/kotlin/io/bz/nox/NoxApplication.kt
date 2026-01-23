package io.bz.nox

import android.app.Application
import android.content.Context
import io.bz.data.di.dataModule
//import io.bz.data.di.domainModule
import io.bz.nox.di.appModule
import io.bz.nox.features.auth.presentation.authPresentationModule
import io.bz.nox.features.chats.presentation.list.chatsPresentationModule
import io.bz.nox.features.settings.presentation.settingsPresentationModule
import io.bz.nox.features.users.presentation.usersPresentationModule
import org.koin.core.context.startKoin

class NoxApplication: Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            modules(
                appModule,
                dataModule,
//                domainModule,
                authPresentationModule,
                settingsPresentationModule,
                usersPresentationModule,
                chatsPresentationModule,
            )
        }
    }
}