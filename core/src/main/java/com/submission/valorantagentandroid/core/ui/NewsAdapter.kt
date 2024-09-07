package com.submission.valorantagentandroid.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.valorantagentandroid.core.R
import com.submission.valorantagentandroid.core.databinding.ItemNewsBinding
import com.submission.valorantagentandroid.core.domain.model.News
import com.submission.valorantagentandroid.core.utils.DateFormatter

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    private var listData = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(agentListData: List<News>?) {
        if (agentListData == null) return
        listData.clear()
        listData.addAll(agentListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater
                .from(parent.context).inflate(R.layout.item_news, parent, false)
        )


    override fun onBindViewHolder(holder: NewsAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNewsBinding.bind(itemView)

        fun bind(data: News) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .into(ivNewsImage)
                tvTitleNews.text = data.title
                tvDescriptionNews.text = data.description
                tvPublishedNews.text = DateFormatter.formatDate(data.publishedAt.toString())
            }
        }

        init {
            binding.root.setOnClickListener {
                @Suppress("DEPRECATION")
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
