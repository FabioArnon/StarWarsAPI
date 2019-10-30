package com.example.starwarsapi.domain.people

import com.example.starwarsapi.models.people.PeopleResponse
import com.example.starwarsapi.service.Result

interface ShowPeopleInteractor {

    fun getListPeople(
        currentPage: Int,
        search: String = "",
        onResult: (Result<PeopleResponse?>) -> Unit
    )

}