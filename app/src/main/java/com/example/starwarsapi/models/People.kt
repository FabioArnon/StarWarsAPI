package com.example.starwarsapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
    @SerializedName("films") @Expose val films: Any? = null,
    @SerializedName("species") @Expose val species: Any? = null,
    @SerializedName("starships") @Expose val starships: Any? = null,
    @SerializedName("vehicles") @Expose val vehicles: Any? = null,
    @SerializedName("url") @Expose val url: Any? = null,
    @SerializedName("created") @Expose val created: Any? = null,
    @SerializedName("edited") @Expose val edited: Any? = null
) : Serializable