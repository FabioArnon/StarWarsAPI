package com.example.starwarsapi.domain

import com.example.starwarsapi.models.People
import com.example.starwarsapi.service.Result

interface DetailPlanetInteractor: BaseFilmInteractor {

    fun getPeopleId(url: List<String>, onResult: (Result<List<People>>) -> Unit)

}