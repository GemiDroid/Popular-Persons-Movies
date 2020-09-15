package com.gemidroid.data.repository

import com.gemidroid.data.model.PersonDetails
import com.gemidroid.data.model.PersonImages
import io.reactivex.rxjava3.core.Single

interface PersonDetailsRepository {
    fun getPersonDetails(personId: Int): Single<PersonDetails>
    fun getPersonImages(personId: Int): Single<PersonImages>
}