package com.bentley.imagesearch.data.repository

import com.bentley.imagesearch.domain.Image


interface SearchRepository {

    suspend fun searchUsers(query: String, page: Int): List<Image>
}