package com.example.starwarsapi.repository

import com.example.starwarsapi.models.StarshipResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowStarshipRepositoryImpl(private val client: RetrofitInterface): ShowStarshipRepository{
    override suspend fun getListStarships(currentPage: Int): Result<StarshipResponse?> {
        val response = client.getstarships(currentPage)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }
}