package com.bentley.imagesearch.domain

import com.bentley.imagesearch.data.repository.SearchRepositoryImpl

class SearchUseCase(private val searchRepository: SearchRepositoryImpl) {

    suspend fun search(query: String, page: Int = 1): SearchResult {
        return searchRepository.search(query, page)
    }
}
