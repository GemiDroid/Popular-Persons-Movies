package com.gemidroid.data.model

import com.google.gson.annotations.SerializedName

data class Persons(
    @SerializedName("results")
    val popularPersons: List<PopularPerson>
)

data class PopularPerson(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val imgProfile: String,
    @SerializedName("known_for")
    val works: List<Work>
)

data class Work(
    @SerializedName(value = "title", alternate = ["name"])
    val title: String? = ""
)
