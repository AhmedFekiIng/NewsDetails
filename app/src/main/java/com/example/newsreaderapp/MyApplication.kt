package com.example.newsreaderapp

import android.app.Application
import com.example.data.api.networkModule
import com.example.data.api.repositoryModule
import com.example.newsreaderapp.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}
