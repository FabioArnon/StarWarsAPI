package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.*
import com.example.starwarsapi.domain.ShowStarshipInteractor
import com.example.starwarsapi.models.StarshipResponse
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.service.Result

class ShowStarshipViewModel(
    dispatcherProvider: DispatcherProvider
): BaseViewModel(dispatcherProvider) {
    private val interactor: ShowStarshipInteractor by interactor()

    private val starshipLiveData = MutableLiveData<ViewState<List<Starships>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Starships>, ViewModelStatusEnum>> = starshipLiveData

    fun getListStarship(query: String = search, isNext: Boolean = false){
        checkSearch(isNext)
        search = query
        starshipLiveData.postLoading()
        interactor.getListStarship(currentPage, search){
            when(it){
                is Result.Success -> onSuccessGetListStarship(it.data)
                is Result.Failure -> onErrorGetListStarship(it.throwable)
            }
        }
    }

    private fun checkSearch(isNext: Boolean) {
        if(!isNext){
            currentPage = 1
        }
    }

    private fun onErrorGetListStarship(throwable: Throwable) {
        error = true
        starshipLiveData.postError(throwable)
    }

    private fun onSuccessGetListStarship(data: StarshipResponse?) {
        data?.let {
            if(!data.starships.isNullOrEmpty()){
                maxPage = pages(data.count)
                starshipLiveData.postSuccess(data.starships)
            } else {
                noMoreResults = true
                starshipLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }


    override fun nextPage() {
        super.nextPage()
        if (maxPage >= currentPage) {
            getListStarship(search, true)
        }

    }
}
