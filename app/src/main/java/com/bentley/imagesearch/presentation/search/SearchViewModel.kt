package com.bentley.imagesearch.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bentley.imagesearch.domain.SearchUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(private val searchUsecase: SearchUseCase) : ViewModel() {

    fun search() {
        viewModelScope.launch {
            searchUsecase.searchUsers("bentley")
                .onEach { dataState ->
                    Timber.d(dataState.toString())
                }
                .launchIn(viewModelScope)
        }
    }
}