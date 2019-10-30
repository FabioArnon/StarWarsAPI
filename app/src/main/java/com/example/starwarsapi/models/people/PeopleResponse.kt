package com.example.starwarsapi.models.people

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("results") @Expose val peoples: List<People>,
    @SerializedName("count") @Expose val count: Int
)