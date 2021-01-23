package com.bentley.imagesearch.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.bentley.imagesearch.databinding.ItemSearchResultBinding
import com.bentley.imagesearch.domain.Image
import com.bentley.imagesearch.utils.Diff

class SearchListAdapter(
    private val list: MutableList<Image>
) :
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
                ivImg.load(item.thumbnailUrl)

                imageItem.setOnClickListener {
                    it.findNavController()
                        .navigate(
                            SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                                item.imageUrl,
                                item.siteName,
                                item.datetime
                            )
                        )
                }
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
        val diffCallback = Diff(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        list.clear()
        list.addAll(newList)
//        notifyDataSetChanged()
        diffResult.dispatchUpdatesTo(this)
    }

    fun add(newList: List<Image>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }
}