package com.gemidroid.data.repository

import com.gemidroid.data.model.Persons
import io.reactivex.rxjava3.core.Single

interface PopularPersonsRepository {
    fun getPopularPersons(pageNumber: Int): Single<Persons>
}