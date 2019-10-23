package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.models.Films
import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.repository.ShowFilmRepository
import com.example.starwarsapi.repository.ShowStarshipRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPeopleViewModel(
    private val repository: ShowFilmRepository,
    private val repository2: ShowStarshipRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val filmLiveData = MutableLiveData<ViewState<List<Films>, ViewModelStatusEnum>>()
    private val starshipLiveData = MutableLiveData<ViewState<List<Starships>, ViewModelStatusEnum>>()

    fun getList(): LiveData<ViewState<List<Films>, ViewModelStatusEnum>> = filmLiveData
    fun getListStarships(): LiveData<ViewState<List<Starships>, ViewModelStatusEnum>> = starshipLiveData

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

    fun nextFilm(people: People) {
        getFilmsId(people.films.map {
            it.replace("https://swapi.co/api/films/", "")
        })
    }

    fun nextStarship(people: People) {
        getStarshipsId(people.starships.map {
            it.replace("https://swapi.co/api/starships/", "")
        })
    }

    fun getStarshipsId(id: List<String>) {
        starshipLiveData.postValue(ViewState(status = ViewModelStatusEnum.LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository2.getStarshipsId(id)
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
}