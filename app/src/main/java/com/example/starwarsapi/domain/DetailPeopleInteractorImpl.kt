package com.example.starwarsapi.domain

import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.repository.ShowFilmRepository
import com.example.starwarsapi.repository.ShowStarshipRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPeopleInteractorImpl(
    repository: ShowFilmRepository,
    private val repositoryStarship: ShowStarshipRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
) : BaseFilmInteractorImpl(
    repository, dispatcherProvider, scope
), DetailPeopleInteractor {


    override fun getStarshipId(url: List<String>, onResult: (Result<List<Starships>>) -> Unit) {
        val list = mutableListOf<Starships>()
        scope.launch(dispatcherProvider.io) {
            getIdsFromUrls(url, "starships").map {
                getStarshipById(it)?.let { starships -> list.add(starships) }
            }
            withContext(dispatcherProvider.ui) {
                onResult(verifySuccess(list, url))
            }
        }
    }

    suspend fun getStarshipById(it: String): Starships? {
        val response = repositoryStarship.getStarshipsId(it)
        return when (response) {
            is Result.Success -> response.data
            is Result.Failure -> null
        }
    }
}
