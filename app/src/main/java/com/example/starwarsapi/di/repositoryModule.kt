package com.example.starwarsapi.di

import com.example.starwarsapi.repository.film.ShowFilmRepository
import com.example.starwarsapi.repository.film.ShowFilmRepositoryImpl
import com.example.starwarsapi.repository.people.ShowPeopleRepository
import com.example.starwarsapi.repository.people.ShowPeopleRepositoryImpl
import com.example.starwarsapi.repository.planet.ShowPlanetRepository
import com.example.starwarsapi.repository.planet.ShowPlanetRepositoryImpl
import com.example.starwarsapi.repository.specie.ShowSpecieRepository
import com.example.starwarsapi.repository.specie.ShowSpecieRepositoryImpl
import com.example.starwarsapi.repository.starship.ShowStarshipRepository
import com.example.starwarsapi.repository.starship.ShowStarshipRepositoryImpl
import com.example.starwarsapi.repository.vehicle.ShowVehicleRepository
import com.example.starwarsapi.repository.vehicle.ShowVehicleRepositoryImpl
import com.example.starwarsapi.service.Networking
import org.koin.dsl.module

val repositoryModule = module {
    factory { Networking.retrofitInterface() }
    factory<ShowPeopleRepository> {
        ShowPeopleRepositoryImpl(
            get()
        )
    }
    factory<ShowPlanetRepository> {
        ShowPlanetRepositoryImpl(
            get()
        )
    }
    factory<ShowStarshipRepository> {
        ShowStarshipRepositoryImpl(
            get()
        )
    }
    factory<ShowVehicleRepository> {
        ShowVehicleRepositoryImpl(
            get()
        )
    }
    factory<ShowSpecieRepository> {
        ShowSpecieRepositoryImpl(
            get()
        )
    }
    factory<ShowFilmRepository> {
        ShowFilmRepositoryImpl(
            get()
        )
    }
}