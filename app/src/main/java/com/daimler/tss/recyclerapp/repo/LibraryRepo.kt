package com.daimler.tss.recyclerapp.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.db.SimpleSQLiteQuery
import android.util.Log
import com.daimler.tss.recyclerapp.AppExecutors
import com.daimler.tss.recyclerapp.data.DataGeneration
import com.daimler.tss.recyclerapp.data.Resource
import com.daimler.tss.recyclerapp.db.Book
import com.daimler.tss.recyclerapp.db.BookDao
import com.daimler.tss.recyclerapp.items.Item

/**
 * Created by Norman Hoeller
 */
class LibraryRepo(private val bookDao: BookDao, private val executors: AppExecutors) {


    fun loadData(sortAscending: Boolean = true): LiveData<Resource<List<Item>>> {
        val result = MediatorLiveData<Resource<List<Item>>>()
        result.value = Resource.loading(null)
        val query = sortBooks(sortAscending)
        executors.diskIO().execute {
            Log.d("LibraryRep - diskIO", "current thread ${Thread.currentThread().name}")
            val fromDB = bookDao.getBooks(query)
            val observer = object : Observer<List<Book>> {
                override fun onChanged(t: List<Book>?) {
                    Log.d("LibraryRep - onChanged", "current thread ${Thread.currentThread().name}")
                    fromDB.removeObserver(this)
                    executors.diskIO().execute {
                        Log.d("LibraryRep - diskIO", "current thread ${Thread.currentThread().name}")
                        if (shouldGenerateBooks(t)) {
                            val bookData = generateBooks()
                            Thread.sleep(2000)
                            bookDao.insertBooks(bookData)
                        }
                    }
                }
            }
            fromDB.observeForever(observer)
            executors.mainThread().execute {
                Log.d("LibraryRep - mainThread", "current thread ${Thread.currentThread().name}")
                result.addSource(bookDao.getBooks(query)) {
                    result.value = if (it != null && it.isNotEmpty()) {
                        val withHeaders = DataGeneration.addSectionHeadersToList(it)
                        Resource.success(withHeaders)
                    } else {
                        Resource.error("no data there", null)
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