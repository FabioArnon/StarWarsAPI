package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.xt.interactor
import com.example.starwarsapi.application.xt.postError
import com.example.starwarsapi.application.xt.postStatus
import com.example.starwarsapi.application.xt.postSuccess
import com.example.starwarsapi.domain.film.DetailFilmInteractor
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.presentation.base.BaseViewModel
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.service.Result

class DetailFilmViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: DetailFilmInteractor by interactor()

    private val starshipLiveData = MutableLiveData<ViewState<List<Starships>, ViewModelStatusEnum>>()
    private val peopleLiveData = MutableLiveData<ViewState<List<People>, ViewModelStatusEnum>>()

    fun getListStarships(): LiveData<ViewState<List<Starships>, ViewModelStatusEnum>> =
        starshipLiveData

    fun getList(): LiveData<ViewState<List<People>, ViewModelStatusEnum>> = peopleLiveData

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
