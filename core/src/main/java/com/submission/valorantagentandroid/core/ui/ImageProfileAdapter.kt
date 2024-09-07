package com.submission.valorantagentandroid.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.submission.valorantagentandroid.core.R
import com.submission.valorantagentandroid.core.databinding.ItemPhotoProfileBinding
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.utils.InsertImageUri.insertGlideImage

class ImageProfileAdapter : RecyclerView.Adapter<ImageProfileAdapter.ListViewHolder>() {

    private var listData = ArrayList<Agent>()
    var onItemClick: ((Agent) -> Unit)? = null
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Agent>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageProfileAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo_profile,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ImageProfileAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data, position == selectedPosition)
    }

    override fun getItemCount() = listData.size

    @Suppress("DEPRECATION")
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPhotoProfileBinding.bind(itemView)

        fun bind(data: Agent, isSelected: Boolean) {
            with(binding) {
                ivImagePhotoProfile.insertGlideImage(
                    itemView.context,
                    data.displayIcon
                )
                ivImagePhotoProfile.alpha = if (isSelected) 0.5f else 1.0f
            }
        }

        init {
            binding.root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(adapterPosition)
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
