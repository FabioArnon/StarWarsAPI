package com.example.starwarsapi.di

import com.example.starwarsapi.domain.*
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    factory { (scope: CoroutineScope) ->
        ShowPeopleInteractorImpl(get(), get(), scope) as ShowPeopleInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowPlanetInteractorImpl(get(), get(), scope) as ShowPlanetInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowFilmInteractorImpl(get(), get(), scope) as ShowFilmInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowSpecieInteractorImpl(get(), get(), scope) as ShowSpecieInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowStarshipInteractorImpl(get(), get(), scope) as ShowStarshipInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowVehicleInteractorImpl(get(), get(), scope) as ShowVehicleInteractor
    }

}