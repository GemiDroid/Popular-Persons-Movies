package com.gemidroid.data.model

import com.google.gson.annotations.SerializedName

data class PersonDetails(
    val id: Int,
    val name: String,
    val biography: String,
    val popularity: Double,
    @SerializedName("place_of_birth")
    val dateOfBirth: String,
    @SerializedName("profile_path")
    val imagePath: String,
    val homepage: String,
    @SerializedName("birthday")
    val birthDay: String,
    @SerializedName("known_for_department")
    val department: String,
    @SerializedName("deathday")
    val deathDay: String? = null,
    @SerializedName("also_known_as")
    val nickNames: List<String>
)
