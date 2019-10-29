package com.example.starwarsapi.repository

import com.example.starwarsapi.application.safeAppCall
import com.example.starwarsapi.models.StarshipResponse
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowStarshipRepositoryImpl(private val client: RetrofitInterface) : ShowStarshipRepository {
    override suspend fun getListStarships(
        currentPage: Int,
        search: String
    ): Result<StarshipResponse?> =
        safeAppCall {
            client.getstarships(currentPage, search)
        }

    override suspend fun getStarshipsId(id: List<String>): Result<List<Starships>> {
        val list = mutableListOf<Starships>()
        id.map {
            when (val response = safeAppCall { client.getStarshipsId(it) }) {
                is Result.Success -> list.add(response.data)
                is Result.Failure -> {
                }
            }
        }
        return if (list.size == id.size) {
            Result.Success(list)
        } else Result.Failure(Throwable("Erro"))
    }
}