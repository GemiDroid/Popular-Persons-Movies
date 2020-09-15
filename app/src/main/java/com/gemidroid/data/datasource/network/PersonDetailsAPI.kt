package com.gemidroid.data.datasource.network

import com.gemidroid.data.model.PersonDetails
import com.gemidroid.data.model.PersonImages
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonDetailsAPI {
    @GET("person/{person_id}")
    fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String

    ): Single<PersonDetails>

    @GET("person/{person_id}/images")
    fun getPersonImages(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Single<PersonImages>
}