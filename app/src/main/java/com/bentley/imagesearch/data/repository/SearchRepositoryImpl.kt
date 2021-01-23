package com.bentley.imagesearch.data.repository

import com.bentley.imagesearch.data.api.ApiService
import com.bentley.imagesearch.data.mapper.ImageMapper
import com.bentley.imagesearch.domain.SearchResult


class SearchRepositoryImpl constructor(
    private val apiService: ApiService,
    private val imageMapper: ImageMapper
) : SearchRepository {
    override suspend fun search(query: String, page: Int): SearchResult {

        val result = apiService.searchUsers(query, page, PAGE_SIZE)
        return SearchResult(imageMapper.mapFromEntity(result.imageList), result.metaInfo)
    }

    companion object {
        const val PAGE_SIZE = 30
    }
}