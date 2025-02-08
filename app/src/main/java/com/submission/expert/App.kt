package com.submission.expert

import android.app.Application
import com.submission.core.di.coreModule
import com.submission.expert.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(coreModule, appModule))
        }
    }
}