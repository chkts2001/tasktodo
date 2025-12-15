package com.example.tasktodo.main

import android.app.Application
import com.example.tasktodo.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ToDoList: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ToDoList)
            androidFileProperties()
            modules(appModules)
        }
    }
}