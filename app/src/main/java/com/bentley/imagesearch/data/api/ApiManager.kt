package com.bentley.imagesearch.data.api

import com.bentley.imagesearch.data.util.AuthInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinApiExtension
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@KoinApiExtension
class ApiManager {
    companion object {
        fun getService(): ApiService {
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            val logger = HttpLoggingInterceptor()
            logger.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            logger.setLevel(HttpLoggingInterceptor.Level.BODY)
            with(httpClient) {
                logger.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                logger.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(logger)
                addInterceptor(AuthInterceptor())
            }

            httpClient.addInterceptor { chain: Interceptor.Chain ->
                val request: Request = chain.request().newBuilder()
                    .header("UserBody-Agent", "android")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }

            val client = httpClient.build()

            val retrofit: Retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}