package com.example.starwarsapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
    @SerializedName("pilots") @Expose val pilots: Any? = null,
    @SerializedName("films") @Expose val films: Any? = null,
    @SerializedName("url") @Expose val url: Any? = null,
    @SerializedName("created") @Expose val created: Any? = null,
    @SerializedName("edited") @Expose val edited: Any? = null
): Serializable