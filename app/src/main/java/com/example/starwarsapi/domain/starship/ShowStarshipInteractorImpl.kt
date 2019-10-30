package com.example.starwarsapi.domain.starship

import com.example.starwarsapi.models.starship.StarshipResponse
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.repository.starship.ShowStarshipRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowStarshipInteractorImpl(
    private val repository: ShowStarshipRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
): ShowStarshipInteractor {
    override fun getListStarship(
        currentPage: Int,
        search: String,
        onResult: (Result<StarshipResponse?>) -> Unit
    ) {
        scope.launch(dispatcherProvider.io){
            val response = repository.getListStarships(currentPage, search)
            withContext(dispatcherProvider.ui){
                onResult(response)
            }
        }
    }
}