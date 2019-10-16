package com.example.starwarsapi.di

import com.example.starwarsapi.repository.*
import com.example.starwarsapi.service.Networking
import org.koin.dsl.module

val repositoryModule = module {
    factory { Networking.retrofitInterface() }
    factory<ShowPeopleActivityRepository> { ShowPeopleActivityRepositoryImpl(get()) }
    factory<ShowPlanetActivityRepository> { ShowPlanetActivityRepositoryImpl(get()) }
    factory<ShowStarshipActivityRepository> { ShowStarshipActivityRepositoryImpl(get()) }
    factory<ShowVehicleActivityRepository> { ShowVehicleActivityRepositoryImpl(get()) }
    factory<ShowSpecieActivityRepository> { ShowSpecieActivityRepositoryImpl(get()) }
    factory<ShowFilmActivityRepository> { ShowFilmActivityRepositoryImpl(get()) }
}