package com.gemidroid.base

import android.app.Application
import com.gemidroid.di.appModule
import com.gemidroid.di.personDetailsModule
import com.gemidroid.di.popularPersonsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        koinStart
    }

    private val koinStart = startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@BaseApp)
        modules(appModule, popularPersonsModule, personDetailsModule)
    }
}
