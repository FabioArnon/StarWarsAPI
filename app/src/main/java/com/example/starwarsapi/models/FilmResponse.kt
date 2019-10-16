package com.example.starwarsapi.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("results")
    @Expose
    val films: List<Films>
)