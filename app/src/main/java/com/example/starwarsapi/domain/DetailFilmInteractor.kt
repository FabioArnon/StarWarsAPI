package com.example.starwarsapi.domain

import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.service.Result

interface DetailFilmInteractor: BaseFilmInteractor {

    fun getStarshipId(url: List<String>, onResult: (Result<List<Starships>>) -> Unit)

    fun getPeopleId(url: List<String>, onResult: (Result<List<People>>) -> Unit)

}