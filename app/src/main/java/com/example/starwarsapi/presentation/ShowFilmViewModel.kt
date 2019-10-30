package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.xt.*
import com.example.starwarsapi.domain.film.ShowFilmInteractor
import com.example.starwarsapi.models.film.FilmResponse
import com.example.starwarsapi.models.film.Films
import com.example.starwarsapi.presentation.base.BaseViewModel
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.service.Result


class ShowFilmViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: ShowFilmInteractor by interactor()

    private val filmLiveData = MutableLiveData<ViewState<List<Films>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Films>, ViewModelStatusEnum>> = filmLiveData

    fun getListFilms(query: String = search, isNext: Boolean = false) {
        checkSearch(isNext)
        search = query
        filmLiveData.postLoading()
        interactor.getListFilm(currentPage, search) {
            when (it) {
                is Result.Success -> onSuccessGetListFilm(it.data)
                is Result.Failure -> onErrorGetListFilm(it.throwable)
            }
        }
    }

    private fun checkSearch(isNext: Boolean) {
        if (!isNext) {
            currentPage = 1
        }
    }

    private fun onErrorGetListFilm(throwable: Throwable) {
        error = true
        filmLiveData.postError(throwable)
    }

    private fun onSuccessGetListFilm(data: FilmResponse?) {
        data?.let {
            if (!data.films.isNullOrEmpty()) {
                maxPage = pages(data.count)
                filmLiveData.postSuccess(data.films)
            } else {
                noMoreResults = true
                filmLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }

    override fun nextPage() {
        super.nextPage()
        if (maxPage >= currentPage) {
            getListFilms(search, true)
        }
    }
}