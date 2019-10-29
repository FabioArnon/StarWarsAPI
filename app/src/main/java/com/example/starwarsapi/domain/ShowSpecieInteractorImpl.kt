package com.example.starwarsapi.domain

import com.example.starwarsapi.models.SpecieResponse
import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.repository.ShowSpecieRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowSpecieInteractorImpl(
    private val repository: ShowSpecieRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
) : ShowSpecieInteractor {
    override fun getListSpecie(
        currentPage: Int,
        search: String,
        onResult: (Result<SpecieResponse?>) -> Unit
    ) {
        scope.launch(dispatcherProvider.io){
            val response = repository.getListSpecies(currentPage, search)
            withContext(dispatcherProvider.ui){
                onResult(response)
            }
        }
    }
}