package com.bentley.imagesearch.data.repository

import com.bentley.imagesearch.domain.SearchResult


interface SearchRepository {

    suspend fun search(query: String, page: Int): SearchResult
}