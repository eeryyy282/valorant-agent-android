package com.submission.valorantagentandroid

import android.app.Application
import com.submission.valorantagentandroid.core.di.databaseModule
import com.submission.valorantagentandroid.core.di.networkModule
import com.submission.valorantagentandroid.core.di.repositoryModule
import com.submission.valorantagentandroid.di.useCaseModule
import com.submission.valorantagentandroid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
