package com.example.starwarsapi.domain.starship

import com.example.starwarsapi.models.starship.StarshipResponse
import com.example.starwarsapi.service.Result

interface ShowStarshipInteractor {

    fun getListStarship(
        currentPage: Int,
        search: String = "",
        onResult: (Result<StarshipResponse?>) -> Unit
    )
}