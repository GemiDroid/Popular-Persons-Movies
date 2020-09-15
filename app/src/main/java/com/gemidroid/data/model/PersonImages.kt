package com.gemidroid.data.model

import com.google.gson.annotations.SerializedName

data class PersonImages(
    @SerializedName("profiles")
    val personImages: List<Images>
)

data class Images(
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("file_path")
    val imagePath: String?
)