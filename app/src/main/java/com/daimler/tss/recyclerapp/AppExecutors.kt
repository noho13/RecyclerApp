package com.daimler.tss.recyclerapp

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Daimler TSS GmbH on 21.06.18 08:44.
 * Copyright (c) 2018. Daimler AG. All rights reserved.
 */

class AppExecutors(
        private val diskIO: Executor,
        private val mainThread: Executor
) {

    constructor() : this(
            Executors.newSingleThreadExecutor(),
            MainThreadExecutor()
    )

    fun diskIO(): Executor {
        return diskIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}