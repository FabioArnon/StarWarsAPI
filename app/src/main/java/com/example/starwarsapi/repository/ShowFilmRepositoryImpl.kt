package com.example.starwarsapi.repository

import com.example.starwarsapi.application.safeAppCall
import com.example.starwarsapi.models.FilmResponse
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface


class ShowFilmRepositoryImpl(private val client: RetrofitInterface) : ShowFilmRepository {
    override suspend fun getListFilm(currentPage: Int, search: String): Result<FilmResponse?> =
        safeAppCall {
            client.getFilms(currentPage, search)
        }

    override suspend fun getFilmsId(id: String): Result<Films> =
        safeAppCall {
            client.getFilmsId(id)
        }
}
