package com.example.starwarsapi.service

import com.example.starwarsapi.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("films/")
    suspend fun getFilms(@Query("page") page: Int): Response<FilmResponse>

    @GET("people/")
    suspend fun getpeople(@Query("page") page: Int): Response<PeopleResponse>

    @GET("planets/")
    suspend fun getplanets(@Query("page") page: Int): Response<PlanetResponse>

    @GET("species/")
    suspend fun getspecies(@Query("page") page: Int): Response<SpecieResponse>

    @GET("starships/")
    suspend fun getstarships(@Query("page") page: Int): Response<StarshipResponse>

    @GET("vehicles/")
    suspend fun getvehicles(@Query("page") page: Int): Response<VehicleResponse>
}