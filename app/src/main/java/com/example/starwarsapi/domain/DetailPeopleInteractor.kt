package com.example.starwarsapi.domain

import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.service.Result

interface DetailPeopleInteractor: BaseFilmInteractor {

    fun getStarshipId(url: List<String>, onResult: (Result<List<Starships>>) -> Unit)

}