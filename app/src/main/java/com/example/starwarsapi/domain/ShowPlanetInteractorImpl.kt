package com.example.starwarsapi.domain

import com.example.starwarsapi.models.PlanetResponse
import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.repository.ShowPlanetRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowPlanetInteractorImpl(
    private val repository: ShowPlanetRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
): ShowPlanetInteractor{

    override fun getListPlanet(
        currentPage: Int,
        search: String,
        onResult: (Result<PlanetResponse?>) -> Unit
    ) {
        scope.launch(dispatcherProvider.io){
            val response = repository.getListPlanet(currentPage, search)
            withContext(dispatcherProvider.ui){
                onResult(response)
            }
        }
    }
}