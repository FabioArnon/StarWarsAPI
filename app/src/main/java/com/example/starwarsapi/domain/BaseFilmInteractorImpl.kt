package com.example.starwarsapi.domain

import com.example.starwarsapi.application.xt.BASE_URL
import com.example.starwarsapi.application.xt.ERRO_DEFAULT
import com.example.starwarsapi.application.xt.FILMS
import com.example.starwarsapi.models.film.Films
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.repository.film.ShowFilmRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseFilmInteractorImpl(
    private val repository: ShowFilmRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
) : BaseFilmInteractor {

    override fun getFilmId(url: List<String>, onResult: (Result<List<Films>>) -> Unit) {
        val list = mutableListOf<Films>()
        scope.launch(dispatcherProvider.io) {
            getIdsFromUrls(url, FILMS).map {
                getFilmById(it)?.let { films -> list.add(films) }
            }
            withContext(dispatcherProvider.ui) {
                onResult(verifySuccess(list, url))
            }
        }
    }

    suspend fun getFilmById(it: String): Films? {
        val response = repository.getFilmsId(it)
        return when (response) {
            is Result.Success -> response.data
            is Result.Failure -> null
        }
    }

    fun<I> verifySuccess(list: List<I>, url: List<String>): Result<List<I>> {
        return if (list.size == url.size) {
            Result.Success(list)
        } else Result.Failure(Throwable(ERRO_DEFAULT))
    }


    fun getIdsFromUrls(id: List<String>, pattern: String) =
        id.map {
            it.replace("$BASE_URL$pattern/", "")
        }
}