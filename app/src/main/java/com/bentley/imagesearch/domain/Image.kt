package com.bentley.imagesearch.domain

data class Image(
    val datetime: String,
    val siteName: String,
    val height: Int,
    val imageUrl: String,
    val thumbnailUrl: String,
)
