package com.example.starwarsapi.di

import com.example.starwarsapi.repository.*
import com.example.starwarsapi.service.Networking
import org.koin.dsl.module

val repositoryModule = module {
    factory { Networking.retrofitInterface() }
    factory<ShowPeopleRepository> { ShowPeopleRepositoryImpl(get()) }
    factory<ShowPlanetRepository> { ShowPlanetRepositoryImpl(get()) }
    factory<ShowStarshipRepository> { ShowStarshipRepositoryImpl(get()) }
    factory<ShowVehicleRepository> { ShowVehicleRepositoryImpl(get()) }
    factory<ShowSpecieRepository> { ShowSpecieRepositoryImpl(get()) }
    factory<ShowFilmRepository> { ShowFilmRepositoryImpl(get()) }
}