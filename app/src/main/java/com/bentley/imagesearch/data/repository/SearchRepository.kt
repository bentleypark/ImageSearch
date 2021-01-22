package com.bentley.imagesearch.data.repository

import com.bentley.imagesearch.domain.Image


interface SearchRepository {

    suspend fun search(query: String, page: Int): List<Image>
}