package com.daimler.tss.recyclerapp.injection

import android.content.Context
import com.daimler.tss.recyclerapp.AppExecutors
import com.daimler.tss.recyclerapp.db.BookDao
import com.daimler.tss.recyclerapp.db.BookDatabase
import com.daimler.tss.recyclerapp.repo.LibraryRepo
import com.daimler.tss.recyclerapp.viewmodel.ViewModelFactory

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