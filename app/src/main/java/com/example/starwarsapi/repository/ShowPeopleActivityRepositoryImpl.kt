package com.example.starwarsapi.repository

import com.example.starwarsapi.models.PeopleResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowPeopleActivityRepositoryImpl(private val client: RetrofitInterface): ShowPeopleActivityRepository{
    override suspend fun getListPeople(currentPage: Int): Result<PeopleResponse?> {
            val response = client.getpeople(currentPage)
            return if (response.isSuccessful) {
                Result.Success(response.body())
            } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }
}