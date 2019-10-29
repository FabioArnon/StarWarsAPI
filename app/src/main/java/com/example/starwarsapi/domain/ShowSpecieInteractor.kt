package com.example.starwarsapi.domain

import com.example.starwarsapi.models.SpecieResponse
import com.example.starwarsapi.service.Result

interface ShowSpecieInteractor {

    fun getListSpecie(
        currentPage: Int,
        search: String = "",
        onResult: (Result<SpecieResponse?>) -> Unit
    )
}