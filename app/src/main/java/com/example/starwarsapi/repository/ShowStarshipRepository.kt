package com.example.starwarsapi.repository

import com.example.starwarsapi.models.StarshipResponse
import com.example.starwarsapi.service.Result

interface ShowStarshipRepository {
    suspend fun getListStarships(currentPage: Int): Result<StarshipResponse?>
}
