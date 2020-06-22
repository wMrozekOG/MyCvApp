package com.example.mycvapp

import android.app.Application
import com.example.mycvapp.di.applicationModule
import com.example.mycvapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(applicationModule, networkModule)
        }
    }
}