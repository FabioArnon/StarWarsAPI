package com.example.starwarsapi.domain

import com.example.starwarsapi.models.PlanetResponse
import com.example.starwarsapi.service.Result

interface ShowPlanetInteractor {

    fun getListPlanet(
        currentPage: Int,
        search: String = "",
        onResult: (Result<PlanetResponse?>) -> Unit
    )

}