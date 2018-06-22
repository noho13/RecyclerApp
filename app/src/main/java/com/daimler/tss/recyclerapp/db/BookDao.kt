package com.daimler.tss.recyclerapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.db.SupportSQLiteQuery
import android.arch.persistence.room.*


/**
 * Created by Daimler TSS GmbH on 19.06.18 13:46.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
@Dao
interface BookDao {

    @RawQuery(observedEntities = [Book::class])
    fun getBooks(query: SupportSQLiteQuery): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<Book>)


    @Query("DELETE FROM book")
    fun deleteAllBooks()

}