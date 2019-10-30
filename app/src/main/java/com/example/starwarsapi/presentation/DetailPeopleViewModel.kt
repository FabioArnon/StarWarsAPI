package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.xt.interactor
import com.example.starwarsapi.application.xt.postError
import com.example.starwarsapi.application.xt.postStatus
import com.example.starwarsapi.application.xt.postSuccess
import com.example.starwarsapi.domain.people.DetailPeopleInteractor
import com.example.starwarsapi.models.film.Films
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.presentation.base.BaseViewModel
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.service.Result

class DetailPeopleViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: DetailPeopleInteractor by interactor()

    private val filmLiveData = MutableLiveData<ViewState<List<Films>, ViewModelStatusEnum>>()
    private val starshipLiveData =
        MutableLiveData<ViewState<List<Starships>, ViewModelStatusEnum>>()

    fun getList(): LiveData<ViewState<List<Films>, ViewModelStatusEnum>> = filmLiveData
    fun getListStarships(): LiveData<ViewState<List<Starships>, ViewModelStatusEnum>> =
        starshipLiveData

    fun getFilmsId(id: List<String>) {
        interactor.getFilmId(id) {
            when (it) {
                is Result.Success -> onSuccessGetListFilmId(it.data)
                is Result.Failure -> onErrorGetListFilmId(it.throwable)
            }
        }
    }

    private fun onErrorGetListFilmId(throwable: Throwable) {
        error = true
        filmLiveData.postError(throwable)
    }

    private fun onSuccessGetListFilmId(data: List<Films>) {
        data.let {
            if (!data.isNullOrEmpty()) {
                filmLiveData.postSuccess(data)
            } else {
                filmLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }

    fun getStarshipsId(id: List<String>) {
        interactor.getStarshipId(id) {
            when (it) {
                is Result.Success -> onSuccessGetListStarshipId(it.data)
                is Result.Failure -> onErrorGetListStarshipId(it.throwable)
            }
        }
    }

    private fun onErrorGetListStarshipId(throwable: Throwable) {
        error = true
        starshipLiveData.postError(throwable)
    }

    private fun onSuccessGetListStarshipId(data: List<Starships>) {
        data.let {
            if (!data.isNullOrEmpty()) {
                starshipLiveData.postSuccess(data)
            } else {
                starshipLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }
}