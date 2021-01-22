package com.bentley.imagesearch.data.entity

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("documents")
    val imageList: List<ImageEntity>,
)

data class ImageEntity(
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("display_sitename")
    val siteName: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
)
