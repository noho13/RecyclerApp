package com.daimler.tss.recyclerapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.daimler.tss.recyclerapp.repo.LibraryRepo

/**
 * Created by Norman Hoeller
 */
class ViewModelFactory(private val repo: LibraryRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
            return LibraryViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}