package com.bentley.imagesearch.domain

import com.bentley.imagesearch.data.repository.SearchRepository
import com.bentley.imagesearch.data.repository.SearchRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class SearchUseCase(private val searchRepository: SearchRepositoryImpl) {

    suspend fun searchUsers(query: String, page: Int = 1): Flow<DataState<List<Image>>> =
        flow {
            emit(DataState.Loading)
            delay(1000)

            try {
                val searchResult = searchRepository.search(query, page)
                emit(DataState.Success(searchResult))
            } catch (e: Exception) {
                emit(DataState.Error(Exception("오류가 발생했습니다. 잠시 후에 다시 한번 시도해주세요. $e")))
            }
        }
}