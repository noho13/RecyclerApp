package com.daimler.tss.recyclerapp

import android.app.Application
import android.arch.persistence.room.Room
import com.daimler.tss.recyclerapp.db.BookDatabase

/**
 * Created by Daimler TSS GmbH on 19.06.18 13:56.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class App : Application() {

    lateinit var db: BookDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, BookDatabase::class.java, "book-db").build()
    }

}