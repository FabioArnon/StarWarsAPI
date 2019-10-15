package com.example.starwarsapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Films (
    @SerializedName("title") @Expose val title: String,
    @SerializedName("episode_id") @Expose val episodeId: Int,
    @SerializedName("opening_crawl") @Expose val openingCrawl: String,
    @SerializedName("director") @Expose val director: String,
    @SerializedName("producer") @Expose val producer: String,
    @SerializedName("release_date") @Expose val releaseDate: Date,
    @SerializedName("characters") @Expose val people: Any? = null,
    @SerializedName("species") @Expose val species: Any? = null,
    @SerializedName("starships") @Expose val starships: Any? = null,
    @SerializedName("vehicles") @Expose val vehicles: Any? = null,
    @SerializedName("url") @Expose val url: Any? = null,
    @SerializedName("created") @Expose val created: Any? = null,
    @SerializedName("edited") @Expose val edited: Any? = null
): Serializable