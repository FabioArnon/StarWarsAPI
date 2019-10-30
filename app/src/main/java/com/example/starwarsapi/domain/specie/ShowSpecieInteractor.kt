package com.example.starwarsapi.domain.specie

import com.example.starwarsapi.models.specie.SpecieResponse
import com.example.starwarsapi.service.Result

interface ShowSpecieInteractor {

    fun getListSpecie(
        currentPage: Int,
        search: String = "",
        onResult: (Result<SpecieResponse?>) -> Unit
    )
}