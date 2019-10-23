package com.example.starwarsapi.service

import com.example.starwarsapi.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("films/")
    suspend fun getFilms(@Query("page") page: Int): Response<FilmResponse>

    @GET("films/{id}")
    suspend fun getFilmsId(@Path("id") id: String): Response<Films>

    @GET("people/")
    suspend fun getpeople(@Query("page") page: Int): Response<PeopleResponse>

    @GET("people/{id}")
    suspend fun getpeopleId(@Path("id") page: String): Response<People>

    @GET("planets/")
    suspend fun getplanets(@Query("page") page: Int): Response<PlanetResponse>

    @GET("species/")
    suspend fun getspecies(@Query("page") page: Int): Response<SpecieResponse>

    @GET("starships/")
    suspend fun getstarships(@Query("page") page: Int): Response<StarshipResponse>

    @GET("starships/{id}")
    suspend fun getStarshipsId(@Path("id") id: String): Response<Starships>

    @GET("vehicles/")
    suspend fun getvehicles(@Query("page") page: Int): Response<VehicleResponse>
}