package com.skbhati199.androidassignment.app

import android.app.Application
import android.content.Context
import com.skbhati199.androidassignment.di.apiModule
import com.skbhati199.androidassignment.di.netModule
import com.skbhati199.androidassignment.di.repositoryModule
import com.skbhati199.androidassignment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidAssigmentApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AndroidAssigmentApp)
            modules(viewModelModule, apiModule, netModule, repositoryModule)
        }
    }
}