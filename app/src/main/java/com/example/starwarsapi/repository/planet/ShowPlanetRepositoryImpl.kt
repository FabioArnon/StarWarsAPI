package com.example.starwarsapi.repository.planet

import com.example.starwarsapi.application.xt.safeAppCall
import com.example.starwarsapi.models.planet.PlanetResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowPlanetRepositoryImpl(private val client: RetrofitInterface) :
    ShowPlanetRepository {
    override suspend fun getListPlanet(currentPage: Int, search: String): Result<PlanetResponse?> =
        safeAppCall {
            client.getplanets(currentPage, search)
        }
}