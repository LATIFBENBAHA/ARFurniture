package com.benbaha.fria.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromString(data: String?): List<String?>? {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType: Type = object : TypeToken<List<String?>?>() {}.getType()

        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String? {
        return Gson().toJson(list)
    }
}

class ListStringConverter{


}