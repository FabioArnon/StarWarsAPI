package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.repository.ShowFilmActivityRepository
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ShowFilmActivityViewModel(
    private val repository: ShowFilmActivityRepository,
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
                            filmLiveData.postValue(
                                ViewState(
                                    response.data?.films,
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

    override fun nextPage() {
        super.nextPage()
        getListFilms()
    }
}