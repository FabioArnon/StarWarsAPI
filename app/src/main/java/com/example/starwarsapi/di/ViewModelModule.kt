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
    factory { DetailPeopleViewModel(get(), get(),get()) }
    factory { DetailStarshipViewModel(get(), get(),get()) }
    factory { DetailVehicleViewModel(get(), get(),get()) }
    factory { DetailPlanetViewModel(get(), get(),get()) }
    factory { DetailSpecieViewModel(get(), get(),get()) }
    factory { DetailFilmViewModel(get(), get(),get()) }
}