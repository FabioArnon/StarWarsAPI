package com.example.starwarsapi.models.specie

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Species(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("classification") @Expose val classification: String,
    @SerializedName("designation") @Expose val designation: String,
    @SerializedName("average_height") @Expose val averageHeight: String,
    @SerializedName("average_lifespan") @Expose val averageLifespan: String,
    @SerializedName("eye_colors") @Expose val eyeColors: String,
    @SerializedName("hair_colors") @Expose val hairColors: String,
    @SerializedName("skin_colors") @Expose val skinColors: String,
    @SerializedName("language") @Expose val language: String,
    @SerializedName("homeworld") @Expose val homeworld: String,
    @SerializedName("people") @Expose val people: List<String>,
    @SerializedName("films") @Expose val films: List<String>,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("created") @Expose val created: String,
    @SerializedName("edited") @Expose val edited: String
): Parcelable