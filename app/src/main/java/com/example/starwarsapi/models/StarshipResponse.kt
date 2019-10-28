package com.example.starwarsapi.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StarshipResponse(
    @SerializedName("results") @Expose val starships: List<Starships>,
    @SerializedName("count") @Expose val count: Int
)