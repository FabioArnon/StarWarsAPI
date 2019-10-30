package com.example.starwarsapi.repository.people

import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.models.people.PeopleResponse
import com.example.starwarsapi.service.Result


interface ShowPeopleRepository {
    suspend fun getListPeople(currentPage: Int, search: String): Result<PeopleResponse?>
    suspend fun getPeopleId(id: String): Result<People>
}