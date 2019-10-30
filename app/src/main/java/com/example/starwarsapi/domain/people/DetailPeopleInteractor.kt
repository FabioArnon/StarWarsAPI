package com.example.starwarsapi.domain.people

import com.example.starwarsapi.domain.BaseFilmInteractor
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.service.Result

interface DetailPeopleInteractor: BaseFilmInteractor {

    fun getStarshipId(url: List<String>, onResult: (Result<List<Starships>>) -> Unit)

}