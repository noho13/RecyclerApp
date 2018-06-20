package com.daimler.tss.recyclerapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.daimler.tss.recyclerapp.data.DataGeneration
import com.daimler.tss.recyclerapp.data.Resource
import com.daimler.tss.recyclerapp.db.BookDao
import com.daimler.tss.recyclerapp.items.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Daimler TSS GmbH on 18.06.18 15:27.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class LibraryViewModel(private val bookDao: BookDao) : ViewModel() {

    val data = MutableLiveData<Resource<List<Item>>>()
    private lateinit var disposable: Disposable
    private var sortDescending = true

    fun generateData() {
        if (data.value == null) {
            data.value = Resource.loading(null)
            Thread({
                bookDao.deleteAllBooks()
                val list = DataGeneration.generateBooks()
                bookDao.insertAll(list)
            }).start()
        }
    }

    fun loadData() {
        data.value = Resource.loading(null)
        disposable = bookDao
                .getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map {
                    sortDescending = !sortDescending
                    if (sortDescending) it.sortedByDescending { it.publicationDate } else it
                }
                .map { DataGeneration.addSectionHeadersToList(it) }
                .map { Resource.success(it) }
                .subscribe(
                        { data.value = it },
                        { data.value = Resource.error("oops, an error", null) }
                )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
