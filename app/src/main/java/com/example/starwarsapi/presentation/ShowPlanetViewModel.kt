package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.models.Planets
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.repository.ShowPlanetRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowPlanetViewModel(
    private val repository: ShowPlanetRepository,
    private val dispatcherProvider: DispatcherProvider
): BaseViewModel(dispatcherProvider) {
    private val planetLiveData = MutableLiveData<ViewState<List<Planets>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Planets>, ViewModelStatusEnum>> = planetLiveData

    fun getListPlanet(){
        planetLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io){
            val response = repository.getListPlanet(currentPage)
            withContext(dispatcherProvider.ui){
                when(response){
                    is Result.Success -> {
                        if(!response.data?.planets.isNullOrEmpty()){
                            planetLiveData.postValue(
                                ViewState(
                                    response.data?.planets,
                                    SUCCESS
                                )
                            )
                        } else{
                            noMoreResults = true
                            planetLiveData.postValue(
                                ViewState(
                                    response.data?.planets,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure->{
                        error = true
                        planetLiveData.postValue(
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

    fun searchListPlanet(search: String){
        planetLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io){
            val response = repository.searchListPlanet(search)
            withContext(dispatcherProvider.ui){
                when(response){
                    is Result.Success -> {
                        if(!response.data?.planets.isNullOrEmpty()){
                            planetLiveData.postValue(
                                ViewState(
                                    response.data?.planets,
                                    SEARCH
                                )
                            )
                        } else{
                            noMoreResults = true
                            planetLiveData.postValue(
                                ViewState(
                                    response.data?.planets,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure->{
                        error = true
                        planetLiveData.postValue(
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
        getListPlanet()
    }
}
