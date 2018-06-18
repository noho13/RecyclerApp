package com.daimler.tss.recyclerapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.daimler.tss.recyclerapp.items.Item
import com.daimler.tss.recyclerapp.repo.LibraryRepo

/**
 * Created by Daimler TSS GmbH on 18.06.18 15:27.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class LibraryViewModel : ViewModel() {

    private val repo = LibraryRepo()
    val data = MutableLiveData<List<Item>>()

    fun loadData() {
        if (data.value == null) {
            data.value = repo.getData()
        }
    }
}
