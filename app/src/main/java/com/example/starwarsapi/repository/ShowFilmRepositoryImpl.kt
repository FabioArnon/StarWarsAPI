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

    override suspend fun getFilmsId(id: List<String>): Result<List<Films>> {
        val list = mutableListOf<Films>()
        id.map {
            when (val response = safeAppCall { client.getFilmsId(it) }) {
                is Result.Success -> list.add(response.data)
                is Result.Failure -> {
                }
            }
        }
        return if (list.size == id.size) {
            Result.Success(list)
        } else Result.Failure(Throwable("Erro"))
    }
}
