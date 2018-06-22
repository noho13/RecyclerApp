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
    private var sortDescending = false

    init {
        data = Transformations.switchMap(fetchData) { input ->
            if (input == null) {
                AbsentLiveData.create()
            } else {
                repo.loadData(sortDescending)
            }
        }
    }

    fun loadData() {
        sortDescending = !sortDescending
        fetchData.value = true
    }


}
