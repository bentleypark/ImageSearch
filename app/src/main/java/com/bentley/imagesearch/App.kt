package com.bentley.imagesearch

import android.app.Application
import com.bentley.imagesearch.di.KoinModule
import org.koin.core.KoinExperimentalAPI

@KoinExperimentalAPI
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    private fun startKoin() = KoinModule.startKoin(this)
}