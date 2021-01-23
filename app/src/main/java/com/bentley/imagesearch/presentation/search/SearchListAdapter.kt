package com.bentley.imagesearch.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.bentley.imagesearch.databinding.ItemSearchResultBinding
import com.bentley.imagesearch.domain.Image

class SearchListAdapter(
    private val list: MutableList<Image>) :
    RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>() {

    private lateinit var binding: ItemSearchResultBinding

    inner class SearchListViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            binding.apply {
                val imageLoader = ivImg.context.imageLoader
                val request = ImageRequest.Builder(ivImg.context)
                    .data(item.thumbnailUrl)
                    .target(ivImg)
                    .build()
                imageLoader.enqueue(request)
//                ivImg.load(item.thumbnailUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemSearchResultBinding.inflate(layoutInflater)
        return SearchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size

    fun addAll(newList: List<Image>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun add(newList: List<Image>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }
}