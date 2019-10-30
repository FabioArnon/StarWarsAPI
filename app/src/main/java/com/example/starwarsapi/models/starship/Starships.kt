package com.example.starwarsapi.models.starship

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Starships(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("model") @Expose val model: String,
    @SerializedName("starship_class") @Expose val starshipClass: String,
    @SerializedName("manufacturer") @Expose val manufacturer: String,
    @SerializedName("cost_in_credits") @Expose val cost: String,
    @SerializedName("length") @Expose val length: String,
    @SerializedName("crew") @Expose val crew: String,
    @SerializedName("passengers") @Expose val passengers: String,
    @SerializedName("max_atmosphering_speed") @Expose val maxSpeed: String,
    @SerializedName("hyperdrive_rating") @Expose val hyperdriveRating: String,
    @SerializedName("MGLT") @Expose val MGLT: String,
    @SerializedName("cargo_capacity") @Expose val cargoCapacity: String,
    @SerializedName("consumables") @Expose val consumables: String,
    @SerializedName("pilots") @Expose val pilots: List<String>,
    @SerializedName("films") @Expose val films: List<String>,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("created") @Expose val created: String,
    @SerializedName("edited") @Expose val edited: String
): Parcelable