package com.norman.hoeller.recyclerapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.norman.hoeller.recyclerapp.AbsentLiveData
import com.norman.hoeller.recyclerapp.data.Resource
import com.norman.hoeller.recyclerapp.items.Item
import com.norman.hoeller.recyclerapp.repo.LibraryRepo

/**
 * Created by Norman Hoeller
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
