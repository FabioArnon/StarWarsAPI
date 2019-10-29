package com.example.starwarsapi.domain

import com.example.starwarsapi.models.PeopleResponse
import com.example.starwarsapi.service.Result

interface ShowPeopleInteractor {

    fun getListPeople(
        currentPage: Int,
        search: String = "",
        onResult: (Result<PeopleResponse?>) -> Unit
    )

}