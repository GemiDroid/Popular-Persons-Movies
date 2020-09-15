package com.gemidroid.data.repository

import com.gemidroid.data.datasource.network.PopularPersonsAPI
import com.gemidroid.data.model.Persons
import com.gemidroid.util.Constants
import io.reactivex.rxjava3.core.Single

class PopularPersonsRepositoryImpl(private val popularPersonsAPI: PopularPersonsAPI) :
    PopularPersonsRepository {

    override fun getPopularPersons(pageNumber: Int): Single<Persons> {
        return popularPersonsAPI.getPopularPersons(Constants.API_KEY, pageNumber)
    }
}