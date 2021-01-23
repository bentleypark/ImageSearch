package com.bentley.imagesearch.data.entity

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("documents")
    val imageList: List<ImageEntity>,
    @SerializedName("meta")
    val metaInfo: MetaInfo,
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

data class MetaInfo(
    @SerializedName("is_end")
    val isEnd: Boolean,
    @SerializedName("pageable_count")
    val pageCount: Int,
    @SerializedName("total_count")
    val total: Int,
)
