package com.example.starwarsapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
    @SerializedName("people") @Expose val people: Any? = null,
    @SerializedName("films") @Expose val films: Any? = null,
    @SerializedName("url") @Expose val url: Any? = null,
    @SerializedName("created") @Expose val created: Any? = null,
    @SerializedName("edited") @Expose val edited: Any? = null
): Serializable