package com.norman.hoeller.recyclerapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.norman.hoeller.recyclerapp.repo.LibraryRepo

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