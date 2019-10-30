package com.example.starwarsapi.di

import com.example.starwarsapi.domain.film.DetailFilmInteractor
import com.example.starwarsapi.domain.film.DetailFilmInteractorImpl
import com.example.starwarsapi.domain.film.ShowFilmInteractor
import com.example.starwarsapi.domain.film.ShowFilmInteractorImpl
import com.example.starwarsapi.domain.people.DetailPeopleInteractor
import com.example.starwarsapi.domain.people.DetailPeopleInteractorImpl
import com.example.starwarsapi.domain.people.ShowPeopleInteractor
import com.example.starwarsapi.domain.people.ShowPeopleInteractorImpl
import com.example.starwarsapi.domain.planet.DetailPlanetInteractor
import com.example.starwarsapi.domain.planet.DetailPlanetInteractorImpl
import com.example.starwarsapi.domain.planet.ShowPlanetInteractor
import com.example.starwarsapi.domain.planet.ShowPlanetInteractorImpl
import com.example.starwarsapi.domain.specie.DetailSpecieInteractor
import com.example.starwarsapi.domain.specie.DetailSpecieInteractorImpl
import com.example.starwarsapi.domain.specie.ShowSpecieInteractor
import com.example.starwarsapi.domain.specie.ShowSpecieInteractorImpl
import com.example.starwarsapi.domain.starship.ShowStarshipInteractor
import com.example.starwarsapi.domain.starship.ShowStarshipInteractorImpl
import com.example.starwarsapi.domain.vehicle.ShowVehicleInteractor
import com.example.starwarsapi.domain.vehicle.ShowVehicleInteractorImpl
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    factory { (scope: CoroutineScope) ->
        ShowPeopleInteractorImpl(
            get(),
            get(),
            scope
        ) as ShowPeopleInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowPlanetInteractorImpl(
            get(),
            get(),
            scope
        ) as ShowPlanetInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowFilmInteractorImpl(
            get(),
            get(),
            scope
        ) as ShowFilmInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowSpecieInteractorImpl(
            get(),
            get(),
            scope
        ) as ShowSpecieInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowStarshipInteractorImpl(
            get(),
            get(),
            scope
        ) as ShowStarshipInteractor
    }

    factory { (scope: CoroutineScope) ->
        ShowVehicleInteractorImpl(
            get(),
            get(),
            scope
        ) as ShowVehicleInteractor
    }

    factory { (scope: CoroutineScope) ->
        DetailFilmInteractorImpl(
            get(),
            get(),
            get(),
            get(),
            scope
        ) as DetailFilmInteractor
    }
    factory { (scope: CoroutineScope) ->
        DetailPeopleInteractorImpl(
            get(),
            get(),
            get(),
            scope
        ) as DetailPeopleInteractor
    }
    factory { (scope: CoroutineScope) ->
        DetailPlanetInteractorImpl(
            get(),
            get(),
            get(),
            scope
        ) as DetailPlanetInteractor
    }
    factory { (scope: CoroutineScope) ->
        DetailSpecieInteractorImpl(
            get(),
            get(),
            get(),
            scope
        ) as DetailSpecieInteractor
    }

}