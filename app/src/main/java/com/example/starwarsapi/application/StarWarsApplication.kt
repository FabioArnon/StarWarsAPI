package com.example.starwarsapi.application

import android.app.Application
import com.example.starwarsapi.di.domainModule
import com.example.starwarsapi.di.repositoryModule
import com.example.starwarsapi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StarWarsApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StarWarsApplication)
            modules(listOf(viewModelModule, repositoryModule, domainModule))
        }
    }
}