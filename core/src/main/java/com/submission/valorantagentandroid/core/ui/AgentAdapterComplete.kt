package com.submission.valorantagentandroid.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.valorantagentandroid.core.R
import com.submission.valorantagentandroid.core.databinding.ItemAgentCompleteBinding
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.utils.BackgroundInsertorGradient.createGradientDrawable

class AgentAdapterComplete : RecyclerView.Adapter<AgentAdapterComplete.ListViewHolder>() {
    private var listData = ArrayList<Agent>()
    var onItemClick: ((Agent) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Agent>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AgentAdapterComplete.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_agent_complete, parent, false)
        )

    override fun onBindViewHolder(holder: AgentAdapterComplete.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAgentCompleteBinding.bind(itemView)
        fun bind(data: Agent) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.background)
                    .into(ivBackgroundAgent2)
                Glide.with(itemView.context)
                    .load(data.fullPortrait)
                    .into(ivAgent2)
                tvAgentName2.text = data.displayName
                tvDescritionAgent2.text = data.description
                tvAgentDeveloper2.text = data.developerName

                val gradientDrawable = createGradientDrawable(data.backgroundGradientColors)
                cvAgentItem.background = gradientDrawable
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
