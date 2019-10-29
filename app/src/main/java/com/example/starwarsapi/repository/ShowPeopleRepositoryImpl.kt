package com.example.starwarsapi.repository

import com.example.starwarsapi.application.safeAppCall
import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.PeopleResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowPeopleRepositoryImpl(
    private val client: RetrofitInterface
) : ShowPeopleRepository {

    override suspend fun getListPeople(
        currentPage: Int,
        search: String
    ): Result<PeopleResponse?> = safeAppCall {
        client.getpeople(currentPage, search)
    }

    override suspend fun getPeopleId(id: String): Result<People> =
        safeAppCall {
            client.getpeopleId(id)
        }

}