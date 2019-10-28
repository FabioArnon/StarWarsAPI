package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.pages
import com.example.starwarsapi.models.Species
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.repository.ShowSpecieRepository
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ShowSpecieViewModel(
    private val repository: ShowSpecieRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val specieLiveData = MutableLiveData<ViewState<List<Species>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Species>, ViewModelStatusEnum>> = specieLiveData

    fun getListSpecies() {
        specieLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListSpecies(currentPage)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.species.isNullOrEmpty()) {
                            maxPage = pages(response.data?.count!!)
                            specieLiveData.postValue(
                                ViewState(
                                    response.data.species,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            specieLiveData.postValue(
                                ViewState(
                                    response.data?.species,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        specieLiveData.postValue(
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

    fun searchListSpecies() {
        specieLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.searchListSpecies(currentPage, search)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.species.isNullOrEmpty()) {
                            maxPage = pages(response.data?.count!!)
                            specieLiveData.postValue(
                                ViewState(
                                    response.data.species,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            specieLiveData.postValue(
                                ViewState(
                                    response.data?.species,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        specieLiveData.postValue(
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
            searchListSpecies()
        } else {
            search = ""
            getListSpecies()
        }
    }


    override fun nextPage() {
        super.nextPage()
        if(maxPage >= currentPage) {
            if (search == "") getListSpecies()
            else searchListSpecies()

        }
    }
}