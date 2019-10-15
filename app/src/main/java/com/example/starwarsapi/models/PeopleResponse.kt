package com.example.starwarsapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PeopleResponse(
    @SerializedName("results")
    @Expose
    val peoples: List<People>
): Serializable