package com.bentley.imagesearch.data.api

import com.bentley.imagesearch.data.entity.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://dapi.kakao.com"
    }

    @GET("v2/search/image")
    suspend fun searchUsers(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): SearchResponse
}