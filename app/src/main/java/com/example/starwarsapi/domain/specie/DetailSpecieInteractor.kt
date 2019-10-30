package com.example.starwarsapi.domain.specie

import com.example.starwarsapi.domain.BaseFilmInteractor
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.service.Result

interface DetailSpecieInteractor : BaseFilmInteractor {

    fun getPeopleId(url: List<String>, onResult: (Result<List<People>>) -> Unit)

}