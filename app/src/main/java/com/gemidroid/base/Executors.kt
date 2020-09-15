package com.gemidroid.base

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class Executors : IExecutors {
    override fun getMainThread(): Scheduler = AndroidSchedulers.mainThread()
    override fun getIOThread(): Scheduler = Schedulers.io()
}
