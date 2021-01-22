package com.bentley.imagesearch.data.mapper

import com.bentley.imagesearch.data.entity.ImageEntity
import com.bentley.imagesearch.domain.Image

class ImageMapper : EntityMapper<List<ImageEntity>, List<Image>> {
    override fun mapFromEntity(entity: List<ImageEntity>): List<Image> {
        return entity.map {
            with(it) {
                Image(datetime, siteName, height, imageUrl, thumbnailUrl)
            }
        }
    }
}
