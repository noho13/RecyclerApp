package com.daimler.tss.recyclerapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.daimler.tss.recyclerapp.db.BookDao
import com.daimler.tss.recyclerapp.repo.LibraryRepo

/**
 * Created by Daimler TSS GmbH on 20.06.18 09:15.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class ViewModelFactory(private val repo: LibraryRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
            return LibraryViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}