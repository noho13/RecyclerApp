package com.daimler.tss.recyclerapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.daimler.tss.recyclerapp.AbsentLiveData
import com.daimler.tss.recyclerapp.data.Resource
import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.repo.LibraryRepo

/**
 * Created by Daimler TSS GmbH on 18.06.18 15:27.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class LibraryViewModel(private val repo: LibraryRepo) : ViewModel() {

    private val fetchData = MutableLiveData<Boolean>()
    var data: LiveData<Resource<List<Item>>> = MutableLiveData<Resource<List<Item>>>()
    private var sortDescending = true

    init {
        data = Transformations.switchMap(fetchData) { input ->
            if (input == null) {
                AbsentLiveData.create()
            } else {
                repo.loadData()
            }
        }
    }

//    fun toggleSortOrder(data: LiveData<Resource<List<Item>>>): LiveData<Resource<List<Item>>> {
//
//    }


//    private fun insertHeadersToData(): LiveData<Resource<List<Item>>> {
//
//    }

    fun loadData() {
//        repo.loadData()

        fetchData.value = true
//        data.value = Resource.loading(null)
//        disposable = bookDao
//                .getBooks()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .map {
//                    sortDescending = !sortDescending
//                    if (sortDescending) it.sortedByDescending { it.publicationDate } else it
//                }
//                .map { DataGeneration.addSectionHeadersToList(it) }
//                .map { Resource.success(it) }
//                .subscribe(
//                        { data.value = it },
//                        { data.value = Resource.error("oops, an error", null) }
//                )
    }


}
