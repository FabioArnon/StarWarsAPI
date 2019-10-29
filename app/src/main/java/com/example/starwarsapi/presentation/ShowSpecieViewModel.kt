package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.*
import com.example.starwarsapi.domain.ShowSpecieInteractor
import com.example.starwarsapi.models.SpecieResponse
import com.example.starwarsapi.models.Species
import com.example.starwarsapi.service.Result


class ShowSpecieViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: ShowSpecieInteractor by interactor()

    private val specieLiveData = MutableLiveData<ViewState<List<Species>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Species>, ViewModelStatusEnum>> = specieLiveData

    fun getListSpecies(query: String = search, isNext: Boolean = false) {
        checkSearch(isNext)
        search = query
        specieLiveData.postLoading()
        interactor.getListSpecie(currentPage, search){
            when(it){
                is Result.Success -> onSuccessGetListSpecie(it.data)
                is Result.Failure -> onErrorGetListSpecie(it.throwable)
            }
        }
    }

    private fun checkSearch(isNext: Boolean) {
        if(!isNext){
            currentPage = 1
        }
    }

    private fun onErrorGetListSpecie(throwable: Throwable) {
        error = true
        specieLiveData.postError(throwable)
    }

    private fun onSuccessGetListSpecie(data: SpecieResponse?) {
        data?.let{
            if(!data.species.isNullOrEmpty()){
                maxPage = pages(data.count)
                specieLiveData.postSuccess(data.species)
            } else {
                noMoreResults = true
                specieLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }


    override fun nextPage() {
        super.nextPage()
        if(maxPage >= currentPage) {
           getListSpecies(search, true)
        }
    }
}