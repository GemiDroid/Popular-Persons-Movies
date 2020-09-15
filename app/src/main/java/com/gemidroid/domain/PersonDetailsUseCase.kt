package com.gemidroid.domain

import androidx.lifecycle.MutableLiveData
import com.gemidroid.base.IExecutors
import com.gemidroid.base.UseCase
import com.gemidroid.data.model.PersonDetails
import com.gemidroid.data.model.PersonImages
import com.gemidroid.data.repository.PersonDetailsRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PersonDetailsUseCase(
    private val executors: IExecutors,
    private val repository: PersonDetailsRepository,
    private val compositeDisposable: CompositeDisposable
) : UseCase<Int, Unit> {


    override fun execute(personId: Int) {
        compositeDisposable.add(
            repository.getPersonDetails(personId)
                .doFinally { getStatus.postValue(false) }
                .subscribeOn(executors.getIOThread())
                .subscribe({
                    getPersonDetails.postValue(it)
                }, {
                    getError.postValue(it)
                })
        )

        compositeDisposable.add(
            repository.getPersonImages(personId)
                .doFinally { getStatus.postValue(false) }
                .subscribeOn(executors.getIOThread())
                .subscribe({
                    getPersonImages.postValue(it)
                }, {
                    getError.postValue(it)
                })
        )
    }

    override fun flushResources() {
        if (compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    val getPersonDetails = MutableLiveData<PersonDetails>()
    val getPersonImages = MutableLiveData<PersonImages>()
    val getError = MutableLiveData<Throwable>()
    val getStatus = MutableLiveData<Boolean>()
}