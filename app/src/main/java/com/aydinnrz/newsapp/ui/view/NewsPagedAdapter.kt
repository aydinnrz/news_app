package com.aydinnrz.newsapp.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aydinnrz.newsapp.data.model.Article
import com.aydinnrz.newsapp.databinding.ItemRecviewNewsBinding
import com.aydinnrz.newsapp.domain.util.ImageUtils.loadWithCoil

class NewsPagedAdapter(
    private val listener: OnItemClickListener
) : PagingDataAdapter<Article, NewsPagedAdapter.MyViewHolder>(diffCallback) {

    // pass selected news url to fragment
    interface OnItemClickListener {
        fun onItemClick(url: String?)
    }

    inner class MyViewHolder(val binding: ItemRecviewNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                getItem(bindingAdapterPosition)?.let { currentItem ->
                    currentItem.url?.let { url -> listener.onItemClick(url = url) }
                }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        // bind data to views in current item of recyclerview
        with(holder.binding) {
            currentItem?.apply {
                title?.let { title ->
                    txtTitle.text = title
                }
                description?.let { description ->
                    txtDescription.text = description
                }
                source?.name?.let { source ->
                    txtSource.text = source
                }
                publishedAt?.let { publishDate ->
                    txtPublishDate.text = publishDate.split("T").toTypedArray()[0]
                }
                // show image with coil extension function
                imgNews.loadWithCoil(urlToImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemRecviewNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}