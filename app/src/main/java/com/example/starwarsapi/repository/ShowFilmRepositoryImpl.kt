package com.example.starwarsapi.repository

import com.example.starwarsapi.models.FilmResponse
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowFilmRepositoryImpl(private val client: RetrofitInterface) : ShowFilmRepository {
    override suspend fun getListFilm(currentPage: Int): Result<FilmResponse?> {
        val response = client.getFilms(currentPage)
        return if (response.isSuccessful) {
            Result.Success(response.body())
        } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }

    override suspend fun getFilmsId(id: List<String>): Result<List<Films>> {
        val list = mutableListOf<Films>()
        id.map {
            val response = client.getFilmsId(it)
            if (response.isSuccessful) {
                response.body()?.let { films -> list.add(films) }
            }
        }
        return if (list.size == id.size) {
            Result.Success(list)
        } else Result.Failure(Throwable("Erro"))
    }
}