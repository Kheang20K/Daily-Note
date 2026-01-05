package com.myapp.dailynote.data.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myapp.dailynote.data.model.SubTaskUi

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromSubTaskList(list: List<SubTaskUi>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toSubTaskList(json: String?): List<SubTaskUi>? {
        if (json.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<SubTaskUi>>() {}.type
        return gson.fromJson(json, type)
    }
}
