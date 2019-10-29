package com.example.starwarsapi.domain

import com.example.starwarsapi.models.FilmResponse
import com.example.starwarsapi.service.Result

interface ShowFilmInteractor {

    fun getListFilm(
        currentPage: Int,
        search: String = "",
        onResult: (Result<FilmResponse?>) -> Unit
    )
}