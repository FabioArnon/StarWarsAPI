package com.example.starwarsapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class VehicleResponse(
    @SerializedName("results") @Expose val vehicles: List<Vehicles>,
    @SerializedName("count") @Expose val count: Int
)