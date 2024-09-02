package com.submission.valorantagentandroid.core.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.submission.valorantagentandroid.R
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.databinding.ItemAgentBinding

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

    private fun createGradientDrawable(colorStrings: List<String?>?): GradientDrawable {
        val colors = colorStrings?.mapNotNull { colorString ->
            try {
                colorString?.let {
                    val formattedColorString = if (!it.startsWith("#")) "#$it" else it
                    Color.parseColor(formattedColorString)
                }
            } catch (e: IllegalArgumentException) {
                Log.e(
                    "AgentAdapter",
                    "Invalid color string: $colorString"
                )
                null
            }
        }?.toIntArray() ?: intArrayOf()

        val validColors = if (colors.isNotEmpty()) colors else intArrayOf(Color.TRANSPARENT)

        val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.BL_TR, validColors)


        val cornerRadius = 24f
        gradientDrawable.cornerRadius = cornerRadius

        return gradientDrawable
    }


}