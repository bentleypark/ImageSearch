package com.bentley.imagesearch.domain

import com.bentley.imagesearch.data.entity.MetaInfo

data class SearchResult(
    val imageList : List<Image>,
    val metaInfo: MetaInfo
)

data class Image(
    val datetime: String,
    val siteName: String,
    val height: Int,
    val imageUrl: String,
    val thumbnailUrl: String,
)


