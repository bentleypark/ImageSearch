package com.bentley.imagesearch.data.repository

import com.bentley.imagesearch.data.api.ApiService
import com.bentley.imagesearch.data.mapper.ImageMapper
import com.bentley.imagesearch.domain.Image


class SearchRepositoryImpl constructor(
    private val apiService: ApiService,
    private val imageMapper: ImageMapper
) :
    SearchRepository {
    override suspend fun searchUsers(query: String, page: Int): List<Image> {

        val result = apiService.searchUsers(query, page, PAGE_SIZE)
        return imageMapper.mapFromEntity(result.imageList)
    }

    companion object {
        const val PAGE_SIZE = 30
    }
}