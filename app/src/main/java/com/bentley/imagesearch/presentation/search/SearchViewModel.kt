package com.bentley.imagesearch.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bentley.imagesearch.domain.Image
import com.bentley.imagesearch.domain.SearchUseCase
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _searchResult = MutableLiveData<List<Image>>()
    val searchResult: LiveData<List<Image>> = _searchResult

    private val _nextList = MutableLiveData<List<Image>>()
    val nextList: LiveData<List<Image>> get() = _nextList

    private var currentPage = 1
    private var currentQuery = ""

    fun search(query: String) {
        viewModelScope.launch {
            checkNewQuery(query)
            val response = searchUseCase.search(query, currentPage)
            response.let {
                _searchResult.value = it.imageList
            }
        }
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            currentPage += 1
            val response = searchUseCase.search(currentQuery, currentPage)
            response.let {
                _nextList.value = it.imageList
            }
        }
    }

    private fun checkNewQuery(newQuery: String) {
        if (currentQuery.isEmpty()) {
            currentQuery = newQuery
        }

        if (currentQuery != newQuery) {
            currentQuery = newQuery
            currentPage = FIRST_PAGE
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}