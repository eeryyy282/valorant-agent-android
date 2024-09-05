package com.submission.valorantagentandroid.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.valorantagentandroid.core.R
import com.submission.valorantagentandroid.core.databinding.ItemAgentBinding
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.utils.BackgroundInsertorGradient.createGradientDrawable

class AgentAdapter : RecyclerView.Adapter<AgentAdapter.ListViewHolder>() {

    private var listData = ArrayList<Agent>()
    var onItemClick: ((Agent) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Agent>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_agent, parent, false)
        )


    override fun onBindViewHolder(holder: AgentAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAgentBinding.bind(itemView)
        fun bind(data: Agent) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.background)
                    .into(ivBackgroundAgent)
                Glide.with(itemView.context)
                    .load(data.fullPortrait)
                    .into(ivAgent)
                tvNameAgent.text = data.displayName
                tvDescriptionAgent.text = data.description

                val gradientDrawable = createGradientDrawable(data.backgroundGradientColors)
                cvItemAgent.background = gradientDrawable
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


