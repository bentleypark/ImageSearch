package com.bentley.imagesearch.utils

import androidx.recyclerview.widget.DiffUtil
import com.bentley.imagesearch.domain.Image

class Diff(private val oldItems: List<Image>, private val newItems: List<Image>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition].imageUrl
        val newItem = newItems[newItemPosition].imageUrl

        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return oldItem == newItem
    }
}