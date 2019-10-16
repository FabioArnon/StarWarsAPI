package com.example.starwarsapi.di

import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.presentation.ShowVehicleActivityViewModel
import com.example.starwarsapi.presentation.*
import org.koin.dsl.module

val viewModelModule = module{
    factory { DispatcherProvider() }
    factory { MainActivityViewModel(get()) }
    factory { ShowPeopleActivityViewModel(get(),get()) }
    factory { ShowPlanetActivityViewModel(get(), get()) }
    factory { ShowStarshipActivityViewModel(get(), get()) }
    factory { ShowVehicleActivityViewModel(get(), get()) }
    factory { ShowSpecieActivityViewModel(get(), get()) }
    factory { ShowFilmActivityViewModel(get(), get()) }
}