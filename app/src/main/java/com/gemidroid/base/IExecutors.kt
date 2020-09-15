package com.gemidroid.base

import io.reactivex.rxjava3.core.Scheduler

interface IExecutors {
    fun getMainThread(): Scheduler
    fun getIOThread(): Scheduler
}
