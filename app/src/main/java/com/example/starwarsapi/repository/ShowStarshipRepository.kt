package com.example.starwarsapi.repository

import com.example.starwarsapi.models.StarshipResponse
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.service.Result

interface ShowStarshipRepository {
    suspend fun getListStarships(currentPage: Int, search: String): Result<StarshipResponse?>
    suspend fun getStarshipsId(id: String): Result<Starships>
}
