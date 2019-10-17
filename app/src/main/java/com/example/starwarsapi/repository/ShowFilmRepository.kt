package com.example.starwarsapi.repository

import com.example.starwarsapi.models.*
import com.example.starwarsapi.service.Result


interface ShowFilmRepository {
    suspend fun getListFilm(currentPage: Int): Result<FilmResponse?>
}