package com.example.starwarsapi.repository.planet

import com.example.starwarsapi.models.planet.PlanetResponse
import com.example.starwarsapi.service.Result

interface ShowPlanetRepository {
    suspend fun getListPlanet(currentPage: Int, search: String): Result<PlanetResponse?>
}
