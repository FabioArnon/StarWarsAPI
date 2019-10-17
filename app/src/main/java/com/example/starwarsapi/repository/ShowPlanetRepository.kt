package com.example.starwarsapi.repository

import com.example.starwarsapi.models.*
import com.example.starwarsapi.service.Result

interface ShowPlanetRepository {
    suspend fun getListPlanet(currentPage: Int): Result<PlanetResponse?>
}
