package com.daimler.tss.recyclerapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.daimler.tss.recyclerapp.data.DataGeneration
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

    val data = MutableLiveData<List<Item>>()
    lateinit var disposable: Disposable

    fun generateData() {
        Thread({
            val list = DataGeneration.getBooks()
            bookDao.insertAll(list)
        }).start()
    }

    fun loadData() {
        if (data.value == null) {
            disposable = bookDao
                    .getAll()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            {
                                data.value = DataGeneration.addSectionHeadersToList(it)
                            },
                            { t: Throwable -> }
                    )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
