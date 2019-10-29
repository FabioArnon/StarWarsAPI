package com.example.starwarsapi.domain

import com.example.starwarsapi.models.StarshipResponse
import com.example.starwarsapi.service.Result

interface ShowStarshipInteractor {

    fun getListStarship(
        currentPage: Int,
        search: String = "",
        onResult: (Result<StarshipResponse?>) -> Unit
    )
}