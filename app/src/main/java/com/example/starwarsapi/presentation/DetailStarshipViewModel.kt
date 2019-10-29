package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.interactor
import com.example.starwarsapi.application.postError
import com.example.starwarsapi.application.postStatus
import com.example.starwarsapi.application.postSuccess
import com.example.starwarsapi.domain.DetailSpecieInteractor
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.repository.ShowFilmRepository
import com.example.starwarsapi.repository.ShowPeopleRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailStarshipViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: DetailSpecieInteractor by interactor()

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
