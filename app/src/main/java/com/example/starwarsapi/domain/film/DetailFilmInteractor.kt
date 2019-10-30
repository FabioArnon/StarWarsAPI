package com.example.starwarsapi.domain.film

import com.example.starwarsapi.domain.BaseFilmInteractor
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.service.Result

interface DetailFilmInteractor: BaseFilmInteractor {

    fun getStarshipId(url: List<String>, onResult: (Result<List<Starships>>) -> Unit)

    fun getPeopleId(url: List<String>, onResult: (Result<List<People>>) -> Unit)

}