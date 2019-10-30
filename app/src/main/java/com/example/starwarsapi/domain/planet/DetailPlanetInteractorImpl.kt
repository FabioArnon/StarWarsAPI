package com.example.starwarsapi.domain.planet

import com.example.starwarsapi.application.xt.PEOPLE
import com.example.starwarsapi.domain.BaseFilmInteractorImpl
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.repository.film.ShowFilmRepository
import com.example.starwarsapi.repository.people.ShowPeopleRepository
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
            getIdsFromUrls(url, PEOPLE).map{
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