package com.gemidroid.presentation

import androidx.lifecycle.ViewModel
import com.gemidroid.domain.PopularPersonsUseCase

class PopularPersonsViewModel(private val popularPersonsUseCase: PopularPersonsUseCase) :
    ViewModel() {

    init {
        popularPersonsUseCase.execute(1)
    }

    fun loadMore(pageNumber: Int) {
        popularPersonsUseCase.execute(pageNumber)
    }

    val popularPersons = popularPersonsUseCase.getPopularPersons
    val error = popularPersonsUseCase.getError
    val progress = popularPersonsUseCase.showProgress

}