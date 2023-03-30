package com.example.notekmm.android

import android.app.Application
import com.example.notekmm.di.getAllModules
import com.example.notekmm.util.initNappier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initNappier()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(getAllModules())
        }
    }
}