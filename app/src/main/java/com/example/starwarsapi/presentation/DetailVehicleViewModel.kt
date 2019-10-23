package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.models.Vehicles
import com.example.starwarsapi.repository.ShowFilmRepository
import com.example.starwarsapi.repository.ShowPeopleRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailVehicleViewModel(
    private val repository: ShowFilmRepository,
    private val repository2: ShowPeopleRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val filmLiveData = MutableLiveData<ViewState<List<Films>, ViewModelStatusEnum>>()
    private val peopleLiveData = MutableLiveData<ViewState<List<People>, ViewModelStatusEnum>>()

    fun getList(): LiveData<ViewState<List<Films>, ViewModelStatusEnum>> = filmLiveData
    fun getListPeople(): LiveData<ViewState<List<People>, ViewModelStatusEnum>> = peopleLiveData

    fun getFilmsId(id: List<String>) {
        filmLiveData.postValue(ViewState(status = ViewModelStatusEnum.LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getFilmsId(id)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        filmLiveData.postValue(
                            ViewState(
                                response.data,
                                ViewModelStatusEnum.SUCCESS
                            )
                        )
                    }
                    is Result.Failure -> {
                        error = true
                        filmLiveData.postValue(
                            ViewState(
                                null,
                                ViewModelStatusEnum.ERROR,
                                error = response.throwable
                            )
                        )
                    }
                }
            }
        }
    }

    fun nextFilm(vehicle: Vehicles) {
        getFilmsId(vehicle.films.map {
            it.replace("https://swapi.co/api/films/", "")
        })
    }

    fun nextPilot(vehicle: Vehicles) {
        getPeopleId(vehicle.pilots.map {
            it.replace("https://swapi.co/api/people/", "")
        })
    }

    fun getPeopleId(id: List<String>) {
        peopleLiveData.postValue(ViewState(status = ViewModelStatusEnum.LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository2.getPeopleId(id)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        peopleLiveData.postValue(
                            ViewState(
                                response.data,
                                ViewModelStatusEnum.SUCCESS
                            )
                        )
                    }
                    is Result.Failure -> {
                        error = true
                        peopleLiveData.postValue(
                            ViewState(
                                null,
                                ViewModelStatusEnum.ERROR,
                                error = response.throwable
                            )
                        )
                    }
                }
            }
        }
    }
}
