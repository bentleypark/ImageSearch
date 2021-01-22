package com.bentley.imagesearch.di

import com.bentley.imagesearch.domain.SearchUseCase
import com.bentley.imagesearch.presentation.search.SearchFragment
import com.bentley.imagesearch.presentation.search.SearchViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentModule = module {
    fragment { SearchFragment() }
    viewModel { SearchViewModel(get()) }
    single { SearchUseCase(get()) }
}