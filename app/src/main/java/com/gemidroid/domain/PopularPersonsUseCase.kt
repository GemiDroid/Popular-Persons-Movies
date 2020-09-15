package com.gemidroid.domain

import androidx.lifecycle.MutableLiveData
import com.gemidroid.base.IExecutors
import com.gemidroid.base.UseCase
import com.gemidroid.data.model.PopularPerson
import com.gemidroid.data.repository.PopularPersonsRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PopularPersonsUseCase(
    private val executors: IExecutors,
    private val repository: PopularPersonsRepository,
    private val compositeDisposable: CompositeDisposable
) : UseCase<Int, Unit> {

    override fun execute(pageNumber: Int) {
        compositeDisposable.add(
            repository.getPopularPersons(pageNumber)
                .doOnSubscribe { showProgress.postValue(true) }
                .doFinally { showProgress.postValue(false) }
                .subscribeOn(executors.getIOThread())
                .subscribe({
                    getPopularPersons.postValue(it.popularPersons)
                }, {
                    getError.postValue(it)
                })
        )
    }

    override fun flushResources() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    val getPopularPersons = MutableLiveData<List<PopularPerson>>()
    val getError = MutableLiveData<Throwable>()
    val showProgress = MutableLiveData<Boolean>()
}