package com.example.starwarsapi.models.specie


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpecieResponse(
    @SerializedName("results") @Expose val species: List<Species>,
    @SerializedName("count") @Expose val count: Int

)