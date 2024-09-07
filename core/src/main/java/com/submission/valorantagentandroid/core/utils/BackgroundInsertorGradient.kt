package com.submission.valorantagentandroid.core.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log

object BackgroundInsertorGradient {
    fun createGradientDrawable(colorStrings: List<String?>?): GradientDrawable {
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
