package com.example.starwarsapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlanetResponse(
    @SerializedName("results")
    @Expose
    val planets: List<Planets>
): Serializable