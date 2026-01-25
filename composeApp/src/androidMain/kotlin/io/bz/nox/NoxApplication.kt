package io.bz.nox

import android.app.Application
import android.content.Context
//import io.bz.data.di.domainModule
import io.bz.nox.di.initModules
import org.koin.core.context.startKoin

class NoxApplication: Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initModules()
    }
}