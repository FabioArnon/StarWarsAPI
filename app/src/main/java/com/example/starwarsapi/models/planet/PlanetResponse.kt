package com.example.starwarsapi.models.planet

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlanetResponse(
    @SerializedName("results") @Expose val planets: List<Planets>,
    @SerializedName("count") @Expose val count: Int

)