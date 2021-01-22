package com.bentley.imagesearch.data.util

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header("UserBody-Agent", "android")
        requestBuilder.addHeader("Content-Type", "application/json")
        requestBuilder.addHeader("Content-Authorization", AUTHORIZATION_KEY)

        return chain.proceed(requestBuilder.build())
    }

    companion object {
        private const val AUTHORIZATION_KEY = "KakaoAK c41bc2ee2877ca255164c507f31cd2ce"
    }
}
