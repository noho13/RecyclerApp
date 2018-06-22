package com.norman.hoeller.recyclerapp

import android.arch.lifecycle.LiveData

/**
 * Created by Norman Hoeller
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