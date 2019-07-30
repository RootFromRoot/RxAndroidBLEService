package com.vito.bletool.data

import android.support.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()

            androidContext(this@Application)
            modules(applicationModule)

        }
        Timber.plant(Timber.DebugTree())
    }
}