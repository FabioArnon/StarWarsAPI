package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.*
import com.example.starwarsapi.domain.ShowPlanetInteractor
import com.example.starwarsapi.models.PlanetResponse
import com.example.starwarsapi.models.Planets
import com.example.starwarsapi.service.Result

class ShowPlanetViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: ShowPlanetInteractor by interactor()

    private val planetLiveData = MutableLiveData<ViewState<List<Planets>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Planets>, ViewModelStatusEnum>> = planetLiveData

    fun getListPlanet(query: String = search, isNext: Boolean = false) {
        checkSearch(isNext)
        search = query
        planetLiveData.postLoading()
        interactor.getListPlanet(currentPage, search) {
            when (it) {
                is Result.Success -> onSuccessGetListPlanet(it.data)
                is Result.Failure -> onErrorGetListPlanet(it.throwable)
            }
        }
    }


    private fun checkSearch(isNext: Boolean) {
        if (!isNext) {
            currentPage = 1
        }
    }

    private fun onErrorGetListPlanet(throwable: Throwable) {
        error = true
        planetLiveData.postError(throwable)
    }

    private fun onSuccessGetListPlanet(data: PlanetResponse?) {
        data?.let {
            if (!data.planets.isNullOrEmpty()) {
                maxPage = pages(data.count)
                planetLiveData.postSuccess(data.planets)
            } else {
                noMoreResults = true
                planetLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }


    override fun nextPage() {
        super.nextPage()
        if (maxPage >= currentPage) {
            getListPlanet(search, true)
        }
    }
}
