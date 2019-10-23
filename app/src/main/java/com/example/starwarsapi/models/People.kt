package com.example.starwarsapi.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class People(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("birth_year") @Expose val birthYear: String,
    @SerializedName("eye_color") @Expose val eyeColor: String,
    @SerializedName("gender") @Expose val gender: String,
    @SerializedName("hair_color") @Expose val hairColor: String,
    @SerializedName("height") @Expose val height: String,
    @SerializedName("mass") @Expose val mass: String,
    @SerializedName("skin_color") @Expose val skinColor: String,
    @SerializedName("homeworld") @Expose val homeworld: String,
    @SerializedName("films") @Expose val films: List<String>,
    @SerializedName("species") @Expose val species: List<String>,
    @SerializedName("starships") @Expose val starships: List<String>,
    @SerializedName("vehicles") @Expose val vehicles: List<String>,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("created") @Expose val created: String,
    @SerializedName("edited") @Expose val edited: String
): Parcelable