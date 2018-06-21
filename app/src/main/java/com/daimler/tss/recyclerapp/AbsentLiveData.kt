package com.daimler.tss.recyclerapp

import android.arch.lifecycle.LiveData

/**
 * Created by Daimler TSS GmbH on 21.06.18 08:51.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */
class AbsentLiveData<T> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}