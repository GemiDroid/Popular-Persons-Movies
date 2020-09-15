package com.gemidroid.presentation

import androidx.lifecycle.ViewModel
import com.gemidroid.domain.PersonDetailsUseCase

class PersonDetailsViewModel(
    private val personDetailsUseCase: PersonDetailsUseCase
) : ViewModel() {

    fun getPersonDetails(personId: Int) {
        personDetailsUseCase.execute(personId)
    }

    val personDetails = personDetailsUseCase.getPersonDetails
    val personImages = personDetailsUseCase.getPersonImages
    val error = personDetailsUseCase.getError
    val status = personDetailsUseCase.getStatus
}

