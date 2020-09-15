package com.gemidroid.data.datasource.network

import com.gemidroid.data.model.Persons
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularPersonsAPI {
    @GET("person/popular")
    fun getPopularPersons(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Persons>
}