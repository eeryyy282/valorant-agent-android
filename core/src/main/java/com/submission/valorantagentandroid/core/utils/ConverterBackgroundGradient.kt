package com.submission.valorantagentandroid.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterBackgroundGradient {
    @TypeConverter
    fun fromStringList(value: List<String?>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String?): List<String?>? {
        val listType = object : TypeToken<List<String?>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
