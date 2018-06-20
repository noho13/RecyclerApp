package com.daimler.tss.recyclerapp.db

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Created by Daimler TSS GmbH on 20.06.18 08:47.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
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