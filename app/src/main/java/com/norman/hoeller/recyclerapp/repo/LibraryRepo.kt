package com.norman.hoeller.recyclerapp.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.db.SimpleSQLiteQuery
import android.util.Log
import com.norman.hoeller.recyclerapp.AppExecutors
import com.norman.hoeller.recyclerapp.BuildConfig
import com.norman.hoeller.recyclerapp.data.DataGeneration
import com.norman.hoeller.recyclerapp.data.Resource
import com.norman.hoeller.recyclerapp.db.Book
import com.norman.hoeller.recyclerapp.db.BookDao
import com.norman.hoeller.recyclerapp.items.Item

/**
 * Created by Norman Hoeller
 */
class LibraryRepo(private val bookDao: BookDao, private val executors: AppExecutors) {


    fun loadData(sortAscending: Boolean = true): LiveData<Resource<List<Item>>> {
        val result = MediatorLiveData<Resource<List<Item>>>()
        result.value = Resource.loading(null)
        val query = sortBooks(sortAscending)
        executors.diskIO().execute {
            if (BuildConfig.DEBUG) {
                Log.d("LibraryRep - diskIO", "current thread ${Thread.currentThread().name}")
            }
            val fromDB = bookDao.getBooks(query)
            val observer = object : Observer<List<Book>> {
                override fun onChanged(t: List<Book>?) {
                    if (BuildConfig.DEBUG) {
                        Log.d("LibraryRep - onChanged", "current thread ${Thread.currentThread().name}")
                    }
                    fromDB.removeObserver(this)
                    executors.diskIO().execute {
                        if (BuildConfig.DEBUG) {
                            Log.d("LibraryRep - diskIO", "current thread ${Thread.currentThread().name}")
                        }
                        if (shouldGenerateBooks(t)) {
                            val bookData = generateBooks()
                            bookDao.insertBooks(bookData)
                        }
                    }
                }
            }
            fromDB.observeForever(observer)
            executors.mainThread().execute {
                if (BuildConfig.DEBUG) {
                    Log.d("LibraryRep - mainThread", "current thread ${Thread.currentThread().name}")
                }
                result.addSource(bookDao.getBooks(query)) {
                    if (it != null && it.isNotEmpty()) {
                        val withHeaders = DataGeneration.addSectionHeadersToList(it)
                        result.value = Resource.success(withHeaders)
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