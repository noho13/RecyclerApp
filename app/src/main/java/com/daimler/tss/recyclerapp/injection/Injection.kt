package com.daimler.tss.recyclerapp.injection

import android.content.Context
import com.daimler.tss.recyclerapp.db.BookDao
import com.daimler.tss.recyclerapp.db.BookDatabase
import com.daimler.tss.recyclerapp.viewmodel.ViewModelFactory

/**
 * Created by Daimler TSS GmbH on 20.06.18 09:20.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
object Injection {

    private fun provideBookDataSource(context: Context): BookDao {
        val database = BookDatabase.getInstance(context)
        return database.bookDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideBookDataSource(context)
        return ViewModelFactory(dataSource)
    }
}