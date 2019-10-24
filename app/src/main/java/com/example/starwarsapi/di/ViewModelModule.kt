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
    factory { ShowPeopleViewModel(get(),get()) }
    factory { ShowPlanetViewModel(get(), get()) }
    factory { ShowStarshipViewModel(get(), get()) }
    factory { ShowVehicleViewModel(get(), get()) }
    factory { ShowSpecieViewModel(get(), get()) }
    factory { ShowFilmViewModel(get(), get()) }
    factory { DetailPeopleViewModel(get(), get(),get()) }
    factory { DetailStarshipViewModel(get(), get(),get()) }
    factory { DetailVehicleViewModel(get(), get(),get()) }
    factory { DetailPlanetViewModel(get(), get(),get()) }
    factory { DetailSpecieViewModel(get(), get(),get()) }
    factory { DetailFilmViewModel(get(), get(),get()) }
}