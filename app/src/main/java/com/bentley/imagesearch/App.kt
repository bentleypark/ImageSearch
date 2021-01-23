package com.bentley.imagesearch

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.bentley.imagesearch.di.KoinModule
import okhttp3.OkHttpClient
import org.koin.core.KoinExperimentalAPI

@KoinExperimentalAPI
class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    private fun startKoin() = KoinModule.startKoin(this)
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }
            .build()
    }
}