package com.submission.valorantagentandroid.core.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object InsertImageUri {
    fun ImageView.insertGlideImage(context: Context, imageUrl: String?) {
        Glide.with(context)
            .load(imageUrl)
            .into(this)
    }
}