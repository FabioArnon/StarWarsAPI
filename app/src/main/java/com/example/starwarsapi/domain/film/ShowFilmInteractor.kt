package com.example.starwarsapi.domain.film

import com.example.starwarsapi.models.film.FilmResponse
import com.example.starwarsapi.service.Result

interface ShowFilmInteractor {

    fun getListFilm(
        currentPage: Int,
        search: String = "",
        onResult: (Result<FilmResponse?>) -> Unit
    )
}