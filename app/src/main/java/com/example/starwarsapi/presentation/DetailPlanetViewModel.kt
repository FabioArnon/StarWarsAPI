package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.xt.interactor
import com.example.starwarsapi.application.xt.postError
import com.example.starwarsapi.application.xt.postStatus
import com.example.starwarsapi.application.xt.postSuccess
import com.example.starwarsapi.domain.planet.DetailPlanetInteractor
import com.example.starwarsapi.models.film.Films
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.presentation.base.BaseViewModel
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.service.Result

class DetailPlanetViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: DetailPlanetInteractor by interactor()

    private val filmLiveData = MutableLiveData<ViewState<List<Films>, ViewModelStatusEnum>>()
    private val peopleLiveData = MutableLiveData<ViewState<List<People>, ViewModelStatusEnum>>()

    fun getList(): LiveData<ViewState<List<Films>, ViewModelStatusEnum>> = filmLiveData
    fun getListPeople(): LiveData<ViewState<List<People>, ViewModelStatusEnum>> = peopleLiveData

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
            }
        }
    }

    fun getPeopleId(id: List<String>) {
        interactor.getPeopleId(id) {
            when (it) {
                is Result.Success -> onSuccessGetListPeopleId(it.data)
                is Result.Failure -> onErrorGetListPeopleId(it.throwable)
            }
        }
    }

    private fun onErrorGetListPeopleId(throwable: Throwable) {
        error = true
        peopleLiveData.postError(throwable)
    }

    private fun onSuccessGetListPeopleId(data: List<People>) {
        data.let {
            if (!data.isNullOrEmpty()) {
                peopleLiveData.postSuccess(data)
            } else {
                peopleLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }
}
