package com.norman.hoeller.recyclerapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.db.SupportSQLiteQuery
import android.arch.persistence.room.*


/**
 * Created by Norman Hoeller
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