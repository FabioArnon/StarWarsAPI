package com.example.starwarsapi.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    @SerializedName("residents") @Expose val residents: List<String>,
    @SerializedName("films") @Expose val films: List<String>,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("created") @Expose val created: String,
    @SerializedName("edited") @Expose val edited: String
): Parcelable