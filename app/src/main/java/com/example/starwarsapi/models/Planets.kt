package com.example.starwarsapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Planets(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("diameter") @Expose val diameter: String,
    @SerializedName("rotation_period") @Expose val rotationPeriod: String,
    @SerializedName("orbital_period") @Expose val orbitalPeriod: String,
    @SerializedName("gravity") @Expose val gravity: String,
    @SerializedName("population") @Expose val population: String,
    @SerializedName("climate") @Expose val climate: String,
    @SerializedName("terrain") @Expose val terrain: String,
    @SerializedName("surface_water") @Expose val surface_water: String,
    @SerializedName("residents") @Expose val residents: Any? = null,
    @SerializedName("films") @Expose val films: Any? = null,
    @SerializedName("url") @Expose val url: Any? = null,
    @SerializedName("created") @Expose val created: Any? = null,
    @SerializedName("edited") @Expose val edited: Any? = null
)