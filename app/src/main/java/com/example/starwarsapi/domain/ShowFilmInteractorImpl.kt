package com.example.starwarsapi.domain

import com.example.starwarsapi.models.FilmResponse
import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.repository.ShowFilmRepository
import kotlinx.coroutines.CoroutineScope
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowFilmInteractorImpl(
    private val repository: ShowFilmRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
) : ShowFilmInteractor {



    override fun getListFilm(
        currentPage: Int,
        search: String,
        onResult: (Result<FilmResponse?>) -> Unit
    ) {
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListFilm(currentPage, search)
            withContext(dispatcherProvider.ui){
                onResult(response)
            }
        }
    }

}