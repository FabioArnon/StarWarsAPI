package com.example.starwarsapi.domain

import com.example.starwarsapi.models.People
import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.repository.ShowFilmRepository
import com.example.starwarsapi.repository.ShowPeopleRepository
import kotlinx.coroutines.CoroutineScope
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPlanetInteractorImpl(
    repository: ShowFilmRepository,
    private val repositoryPeople: ShowPeopleRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
) : BaseFilmInteractorImpl(
    repository, dispatcherProvider, scope
), DetailPlanetInteractor {

    override fun getPeopleId(url: List<String>, onResult: (Result<List<People>>) -> Unit) {
        val list = mutableListOf<People>()
        scope.launch(dispatcherProvider.io){
            getIdsFromUrls(url, "people").map{
                getPeopleById(it)?.let {people -> list.add(people)}
            }
            withContext(dispatcherProvider.ui){
                onResult(verifySuccess(list, url))
            }
        }
    }

    suspend fun getPeopleById(it: String): People? {
        val response = repositoryPeople.getPeopleId(it)
        return when (response) {
            is Result.Success -> response.data
            is Result.Failure -> null
        }
    }
}