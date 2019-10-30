package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.application.xt.*
import com.example.starwarsapi.domain.people.ShowPeopleInteractor
import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.models.people.PeopleResponse
import com.example.starwarsapi.presentation.base.BaseViewModel
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.service.Result

class ShowPeopleViewModel(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val interactor: ShowPeopleInteractor by interactor()

    private val peopleLiveData = MutableLiveData<ViewState<List<People>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<People>, ViewModelStatusEnum>> = peopleLiveData

    fun getListPeople(query: String = search, isNext: Boolean = false) {
        checkSearch(isNext)
        search = query
        peopleLiveData.postLoading()
        interactor.getListPeople(currentPage, search) {
            when (it) {
                is Result.Success -> onSuccessGetListPeople(it.data)
                is Result.Failure -> onErrorGetListPeople(it.throwable)
            }
        }
    }

    private fun checkSearch(isNext: Boolean) {
        if (!isNext) {
            currentPage = 1
        }
    }

    private fun onErrorGetListPeople(throwable: Throwable) {
        error = true
        peopleLiveData.postError(throwable)
    }

    private fun onSuccessGetListPeople(data: PeopleResponse?) {
        data?.let {
            if (!data.peoples.isNullOrEmpty()) {
                maxPage = pages(data.count)
                peopleLiveData.postSuccess(data.peoples)
            } else {
                noMoreResults = true
                peopleLiveData.postStatus(ViewModelStatusEnum.ERROR_LIST_EMPTY)
            }
        }
    }

    override fun nextPage() {
        super.nextPage()
        if (maxPage >= currentPage) {
            getListPeople(search, true)
        }
    }
}