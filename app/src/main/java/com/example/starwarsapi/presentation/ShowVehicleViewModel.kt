package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.pages
import com.example.starwarsapi.models.Vehicles
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.repository.ShowVehicleRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowVehicleViewModel(
    private val repository: ShowVehicleRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val vehicleLiveData = MutableLiveData<ViewState<List<Vehicles>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Vehicles>, ViewModelStatusEnum>> = vehicleLiveData

    fun getListVehicle() {
        vehicleLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListVehicles(currentPage)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.vehicles.isNullOrEmpty()) {
                            maxPage = pages(response.data?.count!!)
                            vehicleLiveData.postValue(
                                ViewState(
                                    response.data.vehicles,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            vehicleLiveData.postValue(
                                ViewState(
                                    response.data?.vehicles,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        vehicleLiveData.postValue(
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

    fun searchListVehicle() {
        vehicleLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.searchListVehicles(currentPage, search)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.vehicles.isNullOrEmpty()) {
                            maxPage = pages(response.data?.count!!)
                            vehicleLiveData.postValue(
                                ViewState(
                                    response.data.vehicles,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            vehicleLiveData.postValue(
                                ViewState(
                                    response.data?.vehicles,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        vehicleLiveData.postValue(
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

    fun searchController(aux: String) {
        if (search != aux) {
            currentPage = 1
        }
        if (aux != "") {
            search = aux
            searchListVehicle()
        } else {
            search = ""
            getListVehicle()
        }
    }

    override fun nextPage() {
        super.nextPage()
        if (maxPage >= currentPage) {
            if (search == "") getListVehicle()
            else searchListVehicle()
        }

    }
}
