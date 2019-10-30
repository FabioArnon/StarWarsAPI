package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.xt.*
import com.example.starwarsapi.domain.vehicle.ShowVehicleInteractor
import com.example.starwarsapi.models.vehicle.VehicleResponse
import com.example.starwarsapi.models.vehicle.Vehicles
import com.example.starwarsapi.presentation.base.BaseViewModel
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.service.Result


class ShowVehicleViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: ShowVehicleInteractor by interactor()

    private val vehicleLiveData = MutableLiveData<ViewState<List<Vehicles>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Vehicles>, ViewModelStatusEnum>> = vehicleLiveData

    fun getListVehicle(query: String = search, isNext: Boolean = false) {
        checkSearch(isNext)
        search = query
        vehicleLiveData.postLoading()
        interactor.getListVehicle(currentPage, search) {
            when (it) {
                is Result.Success -> onSuccessGetListVehicle(it.data)
                is Result.Failure -> onErrorGetListVehicle(it.throwable)
            }
        }
    }

    private fun checkSearch(isNext: Boolean) {
        if (!isNext) {
            currentPage = 1
        }
    }

    private fun onErrorGetListVehicle(throwable: Throwable) {
        error = true
        vehicleLiveData.postError(throwable)
    }

    private fun onSuccessGetListVehicle(data: VehicleResponse?) {
        data?.let {
            if (!data.vehicles.isNullOrEmpty()) {
                maxPage = pages(data.count)
                vehicleLiveData.postSuccess(data.vehicles)
            } else {
                noMoreResults = true
                vehicleLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }


    override fun nextPage() {
        super.nextPage()
        if (maxPage >= currentPage) {
            getListVehicle(search, true)

        }

    }
}
