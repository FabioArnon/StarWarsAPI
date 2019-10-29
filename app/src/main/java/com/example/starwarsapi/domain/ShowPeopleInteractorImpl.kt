package com.example.starwarsapi.domain

import com.example.starwarsapi.models.PeopleResponse
import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.repository.ShowPeopleRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowPeopleInteractorImpl(
    private val repository: ShowPeopleRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
): ShowPeopleInteractor{

    override fun getListPeople(currentPage: Int, search: String, onResult: (Result<PeopleResponse?>) -> Unit){
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListPeople(currentPage, search)
            withContext(dispatcherProvider.ui){
             onResult(response)
            }
        }
    }
}