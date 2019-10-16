package com.example.starwarsapi.repository

import com.example.starwarsapi.models.SpecieResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowSpecieActivityRepositoryImpl(private val client: RetrofitInterface): ShowSpecieActivityRepository{
    override suspend fun getListSpecies(currentPage: Int): Result<SpecieResponse?> {
        val response = client.getspecies(currentPage)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }
}