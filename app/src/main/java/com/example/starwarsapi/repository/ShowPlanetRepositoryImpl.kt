package com.example.starwarsapi.repository

import com.example.starwarsapi.application.safeAppCall
import com.example.starwarsapi.models.PlanetResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowPlanetRepositoryImpl(private val client: RetrofitInterface): ShowPlanetRepository{
    override suspend fun getListPlanet(currentPage: Int): Result<PlanetResponse?> = safeAppCall {
        client.getplanets(currentPage, "")
    }

    override suspend fun searchListPlanet(currentPage: Int, search: String): Result<PlanetResponse?> = safeAppCall {
        client.getplanets(currentPage, search)
    }
}