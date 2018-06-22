package com.daimler.tss.recyclerapp.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.db.SimpleSQLiteQuery
import com.daimler.tss.recyclerapp.AppExecutors
import com.daimler.tss.recyclerapp.data.DataGeneration
import com.daimler.tss.recyclerapp.data.Resource
import com.daimler.tss.recyclerapp.db.Book
import com.daimler.tss.recyclerapp.db.BookDao
import com.daimler.tss.recyclerapp.items.Item

/**
 * Created by Daimler TSS GmbH on 21.06.18 08:43.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class LibraryRepo(private val bookDao: BookDao, private val executors: AppExecutors) {


    fun loadData(sortAscending: Boolean = true): LiveData<Resource<List<Item>>> {
        val result = MediatorLiveData<Resource<List<Item>>>()
        result.value = Resource.loading(null)
        val query = sortBooks(sortAscending)
        executors.diskIO().execute {
            val fromDB = bookDao.getBooks(query)
            val observer = object : Observer<List<Book>> {
                override fun onChanged(t: List<Book>?) {
                    fromDB.removeObserver(this)
                    executors.diskIO().execute {
                        if (shouldGenerateBooks(t)) {
                            val bookData = generateBooks()
                            bookDao.insertBooks(bookData)
                        }
                    }
                }
            }
            fromDB.observeForever(observer)
            executors.mainThread().execute {
                result.addSource(bookDao.getBooks(query)) {
                    result.value = if (it != null && it.isNotEmpty()) {
                        val withHeaders = DataGeneration.addSectionHeadersToList(it)
                        Resource.success(withHeaders)
                    } else {
                        Resource.error("oops, something went wrong", null)
                    }
                }
            }
        }
        return result
    }

    private fun generateBooks(): MutableList<Book> {
        return MutableList(40) { DataGeneration.getBookItem() }
    }

    private fun shouldGenerateBooks(data: List<Book>?): Boolean {
        return data == null || data.isEmpty()
    }

    private fun sortBooks(sortAscending: Boolean): SimpleSQLiteQuery {
        return if (sortAscending) {
            SimpleSQLiteQuery("SELECT * FROM book ORDER BY publicationDate ASC")
        } else {
            SimpleSQLiteQuery("SELECT * FROM book ORDER BY publicationDate DESC")
        }
    }


}