package com.example.starwarsapi.di

import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.presentation.ShowVehicleViewModel
import com.example.starwarsapi.presentation.*
import com.example.starwarsapi.ui.fragment.BaseFragment
import org.koin.dsl.module

val viewModelModule = module{
    factory { DispatcherProvider() }
    factory { MainViewModel(get()) }
    factory { BaseFragment() }
    factory { ShowPeopleViewModel(get()) }
    factory { ShowPlanetViewModel(get()) }
    factory { ShowStarshipViewModel(get()) }
    factory { ShowVehicleViewModel(get()) }
    factory { ShowSpecieViewModel(get()) }
    factory { ShowFilmViewModel(get()) }
    factory { DetailPeopleViewModel(get()) }
    factory { DetailStarshipViewModel(get()) }
    factory { DetailVehicleViewModel(get()) }
    factory { DetailPlanetViewModel(get()) }
    factory { DetailSpecieViewModel(get()) }
    factory { DetailFilmViewModel(get()) }
}