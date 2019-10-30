package com.example.starwarsapi.domain.planet

import com.example.starwarsapi.models.planet.PlanetResponse
import com.example.starwarsapi.service.Result

interface ShowPlanetInteractor {

    fun getListPlanet(
        currentPage: Int,
        search: String = "",
        onResult: (Result<PlanetResponse?>) -> Unit
    )

}