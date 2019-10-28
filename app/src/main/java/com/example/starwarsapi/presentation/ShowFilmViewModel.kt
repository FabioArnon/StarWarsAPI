package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.pages
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.repository.ShowFilmRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ShowFilmViewModel(
    private val repository: ShowFilmRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val filmLiveData = MutableLiveData<ViewState<List<Films>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Films>, ViewModelStatusEnum>> = filmLiveData

    fun getListFilms() {
        filmLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListFilm(currentPage)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.films.isNullOrEmpty()) {
                            maxPage = pages(response.data?.count!!)
                            filmLiveData.postValue(
                                ViewState(
                                    response.data.films,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            filmLiveData.postValue(
                                ViewState(
                                    response.data?.films,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        filmLiveData.postValue(
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

    fun searchListFilms() {
        filmLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getSearchFilm(search)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.films.isNullOrEmpty()) {
                            maxPage = pages(response.data?.count!!)
                            filmLiveData.postValue(
                                ViewState(
                                    response.data.films,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            filmLiveData.postValue(
                                ViewState(
                                    response.data?.films,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        filmLiveData.postValue(
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
            searchListFilms()
        } else {
            search = ""
            getListFilms()
        }
    }

    override fun nextPage() {
        super.nextPage()
        if(maxPage >= currentPage) {
            if (search == "") getListFilms()
            else searchListFilms()

        }
    }
}