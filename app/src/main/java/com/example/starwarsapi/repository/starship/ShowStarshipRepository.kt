package com.example.starwarsapi.repository.starship

import com.example.starwarsapi.models.starship.StarshipResponse
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.service.Result

interface ShowStarshipRepository {
    suspend fun getListStarships(currentPage: Int, search: String): Result<StarshipResponse?>
    suspend fun getStarshipsId(id: String): Result<Starships>
}
