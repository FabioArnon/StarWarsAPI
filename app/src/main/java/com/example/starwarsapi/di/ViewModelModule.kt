package com.example.starwarsapi.di

import com.example.starwarsapi.DispatcherProvider
import com.example.starwarsapi.presentation.ShowPlanetActivityViewModel
import com.example.starwarsapi.presentation.MainActivityViewModel
import com.example.starwarsapi.presentation.ShowPeopleActivityViewModel
import com.example.starwarsapi.presentation.ShowStarshipActivityViewModel
import org.koin.dsl.module

val viewModelModule = module{
    factory { DispatcherProvider() }
    factory { MainActivityViewModel(get()) }
    factory { ShowPeopleActivityViewModel(get(),get()) }
    factory { ShowPlanetActivityViewModel(get(), get()) }
    factory { ShowStarshipActivityViewModel(get(), get()) }
}