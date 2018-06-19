package com.daimler.tss.recyclerapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Daimler TSS GmbH on 19.06.18 13:48.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}