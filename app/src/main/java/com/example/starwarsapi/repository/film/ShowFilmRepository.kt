package com.example.starwarsapi.repository.film

import com.example.starwarsapi.models.film.FilmResponse
import com.example.starwarsapi.models.film.Films
import com.example.starwarsapi.service.Result


interface ShowFilmRepository {
    suspend fun getListFilm(currentPage: Int, search: String): Result<FilmResponse?>
    suspend fun getFilmsId(id: String): Result<Films>
}