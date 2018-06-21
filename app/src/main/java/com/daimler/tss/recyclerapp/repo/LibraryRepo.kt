package com.daimler.tss.recyclerapp.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
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


    fun loadData(): LiveData<Resource<List<Item>>> {
        val result = MediatorLiveData<Resource<List<Item>>>()
        result.value = Resource.loading(null)
        executors.diskIO().execute {
            val fromDB = bookDao.getBooks()
            fromDB.observeForever {
                if (it?.size == 0) {
                    val bookData = generateData()
                    bookDao.insertAll(bookData)
                }
            }
            executors.mainThread().execute {
                result.addSource(bookDao.getBooks()) {
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

    private fun generateData(): MutableList<Book> {
        return MutableList(40) { DataGeneration.getBookItem() }
    }
}