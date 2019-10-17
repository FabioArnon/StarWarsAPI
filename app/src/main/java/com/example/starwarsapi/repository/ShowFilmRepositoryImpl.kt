package com.example.starwarsapi.repository

import com.example.starwarsapi.models.FilmResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowFilmRepositoryImpl(private val client: RetrofitInterface): ShowFilmRepository{
    override suspend fun getListFilm(currentPage: Int): Result<FilmResponse?> {
        val response = client.getFilms(currentPage)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }
}