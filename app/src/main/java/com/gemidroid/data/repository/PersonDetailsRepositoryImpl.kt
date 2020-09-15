package com.gemidroid.data.repository

import com.gemidroid.data.datasource.network.PersonDetailsAPI
import com.gemidroid.data.model.PersonDetails
import com.gemidroid.data.model.PersonImages
import com.gemidroid.util.Constants
import io.reactivex.rxjava3.core.Single

class PersonDetailsRepositoryImpl(private val personDetailsAPI: PersonDetailsAPI) :
    PersonDetailsRepository {
    override fun getPersonDetails(personId: Int): Single<PersonDetails> {
        return personDetailsAPI.getPersonDetails(personId, Constants.API_KEY)
    }

    override fun getPersonImages(personId: Int): Single<PersonImages> {
        return personDetailsAPI.getPersonImages(personId, Constants.API_KEY)
    }
}