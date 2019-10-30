package com.example.starwarsapi.service

import com.example.starwarsapi.application.xt.*
import com.example.starwarsapi.models.film.FilmResponse
import com.example.starwarsapi.models.film.Films
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.models.people.PeopleResponse
import com.example.starwarsapi.models.planet.PlanetResponse
import com.example.starwarsapi.models.specie.SpecieResponse
import com.example.starwarsapi.models.starship.StarshipResponse
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.models.vehicle.VehicleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    @GET(FILM_ENDPOINT)
    suspend fun getFilms(@Query(PAGE_QUERY) page: Int,
                         @Query(SEARCH_QUERY) query: String): Response<FilmResponse>

    @GET(FILM_ID_ENDPOINT)
    suspend fun getFilmsId(@Path(ID_PATH) id: String): Response<Films>

    @GET(PEOPLE_ENDPOINT)
    suspend fun getpeople(@Query(PAGE_QUERY) page: Int,
                          @Query(SEARCH_QUERY) query: String): Response<PeopleResponse>

    @GET(PEOPLE_ID_ENDPOINT)
    suspend fun getpeopleId(@Path(ID_PATH) page: String): Response<People>

    @GET(PLANET_ENDPOINT)
    suspend fun getplanets(@Query(PAGE_QUERY) page: Int,
                           @Query(SEARCH_QUERY) query: String): Response<PlanetResponse>

    @GET(SPECIE_ENDPOINT)
    suspend fun getspecies(@Query(PAGE_QUERY) page: Int,
                           @Query(SEARCH_QUERY) query: String): Response<SpecieResponse>

    @GET(STARSHIP_ENDPOINT)
    suspend fun getstarships(@Query(PAGE_QUERY) page: Int,
                             @Query(SEARCH_QUERY) query: String): Response<StarshipResponse>

    @GET(STARSHIP_ID_ENDPOINT)
    suspend fun getStarshipsId(@Path(ID_PATH) id: String): Response<Starships>

    @GET(VEHICLE_ENDPOINT)
    suspend fun getvehicles(@Query(PAGE_QUERY) page: Int,
                            @Query(SEARCH_QUERY) query: String): Response<VehicleResponse>
}