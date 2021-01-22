package com.bentley.imagesearch.di

import com.bentley.imagesearch.data.api.ApiManager.Companion.getService
import com.bentley.imagesearch.data.mapper.ImageMapper
import com.bentley.imagesearch.data.repository.SearchRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single { getService() }
    single { SearchRepositoryImpl(get(),get()) }
    single { ImageMapper() }
}