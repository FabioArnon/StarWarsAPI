package com.example.starwarsapi.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StarshipResponse(
    @SerializedName("results")
    @Expose
    val starships: List<Starships>
): Serializable