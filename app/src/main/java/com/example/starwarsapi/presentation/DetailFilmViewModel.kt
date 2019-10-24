package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.repository.ShowPeopleRepository
import com.example.starwarsapi.repository.ShowStarshipRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFilmViewModel(
    private val repository: ShowStarshipRepository,
    private val repository2: ShowPeopleRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val starshipLiveData = MutableLiveData<ViewState<List<Starships>, ViewModelStatusEnum>>()
    private val peopleLiveData = MutableLiveData<ViewState<List<People>, ViewModelStatusEnum>>()

    fun getListStarships(): LiveData<ViewState<List<Starships>, ViewModelStatusEnum>> =
        starshipLiveData

    fun getList(): LiveData<ViewState<List<People>, ViewModelStatusEnum>> = peopleLiveData

    fun getStarshipId(id: List<String>) {
        starshipLiveData.postValue(ViewState(status = ViewModelStatusEnum.LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getStarshipsId(id)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        starshipLiveData.postValue(
                            ViewState(
                                response.data,
                                ViewModelStatusEnum.SUCCESS
                            )
                        )
                    }
                    is Result.Failure -> {
                        error = true
                        starshipLiveData.postValue(
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

    fun nextStarship(film: Films) {
        getStarshipId(film.starships.map {
            it.replace("https://swapi.co/api/starships/", "")
        })
    }

    fun nextPeople(film: Films) {
        getPeopleId(film.people.map {
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