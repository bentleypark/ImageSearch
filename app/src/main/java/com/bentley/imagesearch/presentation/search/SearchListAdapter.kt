package com.bentley.imagesearch.presentation.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.bentley.imagesearch.databinding.ItemSearchResultBinding
import com.bentley.imagesearch.domain.Image
import com.bentley.imagesearch.presentation.detail.DetailActivity
import com.bentley.imagesearch.utils.Diff
import com.bentley.imagesearch.utils.isConnected
import kotlin.reflect.KFunction0

class SearchListAdapter(
    private val list: MutableList<Image>,
    private val networkErrorCallback: KFunction0<Unit>
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
                    if (it.context.isConnected()) {
                        val intent = Intent(it.context, DetailActivity::class.java).apply {
                            putExtra("imageUrl", item.imageUrl)
                            putExtra("siteName", item.siteName)
                            putExtra("datetime", item.datetime)
                        }
                        intent.putExtra("imageUrl", item.imageUrl)
                        it.context.startActivity(intent)
                    } else {
                        networkErrorCallback()
                    }
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

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }
}