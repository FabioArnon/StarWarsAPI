package com.example.starwarsapi.models.vehicle

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicles(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("model") @Expose val model: String,
    @SerializedName("vehicle_class") @Expose val vehicleClass: String,
    @SerializedName("manufacturer") @Expose val manufacturer: String,
    @SerializedName("length") @Expose val length: String,
    @SerializedName("cost_in_credits") @Expose val cost: String,
    @SerializedName("crew") @Expose val crew: String,
    @SerializedName("passengers") @Expose val passengers: String,
    @SerializedName("max_atmosphering_speed") @Expose val maxSpeed: String,
    @SerializedName("cargo_capacity") @Expose val cargoCapacity: String,
    @SerializedName("consumables") @Expose val consumables: String,
    @SerializedName("films") @Expose val films: List<String>,
    @SerializedName("pilots") @Expose val pilots: List<String>,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("created") @Expose val created: String,
    @SerializedName("edited") @Expose val edited: String
): Parcelable