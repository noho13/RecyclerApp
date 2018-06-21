package com.daimler.tss.recyclerapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


/**
 * Created by Daimler TSS GmbH on 19.06.18 13:46.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
@Dao
interface BookDao {

    @Query("SELECT * FROM book ORDER BY publicationDate ASC")
    fun getBooks(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<Book>)


    @Query("DELETE FROM book")
    fun deleteAllBooks()

}