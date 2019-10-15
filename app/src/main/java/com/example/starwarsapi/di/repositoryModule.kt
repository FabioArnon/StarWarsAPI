package com.example.starwarsapi.di

import com.example.starwarsapi.repository.ShowPeopleActivityRepository
import com.example.starwarsapi.repository.ShowPeopleActivityRepositoryImpl
import com.example.starwarsapi.service.Networking
import org.koin.dsl.module

val repositoryModule = module {
    factory { Networking.retrofitInterface() }
    factory<ShowPeopleActivityRepository> { ShowPeopleActivityRepositoryImpl(get()) }
}