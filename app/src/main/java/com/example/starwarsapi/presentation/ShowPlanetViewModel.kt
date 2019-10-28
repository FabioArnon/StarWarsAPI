package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.pages
import com.example.starwarsapi.models.Planets
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.repository.ShowPlanetRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowPlanetViewModel(
    private val repository: ShowPlanetRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val planetLiveData = MutableLiveData<ViewState<List<Planets>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Planets>, ViewModelStatusEnum>> = planetLiveData

    fun getListPlanet() {
        planetLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListPlanet(currentPage)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.planets.isNullOrEmpty()) {
                            maxPage = pages(response.data?.count!!)
                            planetLiveData.postValue(
                                ViewState(
                                    response.data.planets,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            planetLiveData.postValue(
                                ViewState(
                                    response.data?.planets,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        planetLiveData.postValue(
                            ViewState(
                                null,
                                ERROR,
                                error = response.throwable
                            )
                        )
                    }
                }
            }
        }
    }

    fun searchListPlanet() {
        planetLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.searchListPlanet(currentPage, search)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.planets.isNullOrEmpty()) {
                            maxPage = pages(response.data?.count!!)
                            planetLiveData.postValue(
                                ViewState(
                                    response.data.planets,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            planetLiveData.postValue(
                                ViewState(
                                    response.data?.planets,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        planetLiveData.postValue(
                            ViewState(
                                null,
                                ERROR,
                                error = response.throwable
                            )
                        )
                    }
                }
            }
        }
    }

    fun searchController(aux: String) {
        if (search != aux) {
            currentPage = 1
        }
        if (aux != "") {
            search = aux
            searchListPlanet()
        } else {
            search = ""
            getListPlanet()
        }
    }

    override fun nextPage() {
        super.nextPage()
        if (maxPage >= currentPage) {
            if (search == "") getListPlanet()
            else searchListPlanet()
        }
    }
}
