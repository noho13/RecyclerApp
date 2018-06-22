package com.norman.hoeller.recyclerapp.injection

import android.content.Context
import com.norman.hoeller.recyclerapp.AppExecutors
import com.norman.hoeller.recyclerapp.db.BookDao
import com.norman.hoeller.recyclerapp.db.BookDatabase
import com.norman.hoeller.recyclerapp.repo.LibraryRepo
import com.norman.hoeller.recyclerapp.viewmodel.ViewModelFactory

/**
 * Created by Norman Hoeller
 */
object Injection {

    private fun provideBookDataSource(context: Context): BookDao {
        val database = BookDatabase.getInstance(context)
        return database.bookDao()
    }

    private fun provideLibraryRepo(bookDao: BookDao) = LibraryRepo(bookDao, AppExecutors())

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideBookDataSource(context)
        val repo = provideLibraryRepo(dataSource)
        return ViewModelFactory(repo)
    }


}