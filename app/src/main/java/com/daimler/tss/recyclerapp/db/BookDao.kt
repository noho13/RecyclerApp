package com.daimler.tss.recyclerapp.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


/**
 * Created by Daimler TSS GmbH on 19.06.18 13:46.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    @Insert
    fun insertAll(vararg books: Book)

}