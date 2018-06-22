package com.norman.hoeller.recyclerapp.db

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Created by Norman Hoeller
 */
object Converters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}