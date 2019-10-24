package com.example.starwarsapi.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Films (
    @SerializedName("title") @Expose val title: String,
    @SerializedName("episode_id") @Expose val episodeId: Int,
    @SerializedName("opening_crawl") @Expose val openingCrawl: String,
    @SerializedName("director") @Expose val director: String,
    @SerializedName("producer") @Expose val producer: String,
    @SerializedName("release_date") @Expose val releaseDate: Date,
    @SerializedName("characters") @Expose val people: List<String>,
    @SerializedName("species") @Expose val species: List<String>,
    @SerializedName("starships") @Expose val starships: List<String>,
    @SerializedName("vehicles") @Expose val vehicles: List<String>,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("created") @Expose val created: String,
    @SerializedName("edited") @Expose val edited: String
): Parcelable