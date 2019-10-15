package com.example.starwarsapi.repository

import com.example.starwarsapi.models.PlanetResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowPlanetActivityRepositoryImpl(private val client: RetrofitInterface): ShowPlanetActivityRepository{
    override suspend fun getListPlanet(currentPage: Int): Result<PlanetResponse?> {
        val response = client.getplanets(currentPage)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }
}