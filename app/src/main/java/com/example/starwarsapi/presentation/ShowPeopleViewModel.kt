package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.models.People
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.repository.ShowPeopleRepository
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ShowPeopleViewModel(
    private val repository: ShowPeopleRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val peopleLiveData = MutableLiveData<ViewState<List<People>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<People>, ViewModelStatusEnum>> = peopleLiveData

    fun getListPeople() {
        peopleLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListPeople(currentPage)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.peoples.isNullOrEmpty()) {
                            peopleLiveData.postValue(
                                ViewState(
                                    response.data?.peoples,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            peopleLiveData.postValue(
                                ViewState(
                                    response.data?.peoples,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        peopleLiveData.postValue(
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

    fun searchListPeople(search: String) {
        peopleLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getSearchPeople(search)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.peoples.isNullOrEmpty()) {
                            peopleLiveData.postValue(
                                ViewState(
                                    response.data?.peoples,
                                    SEARCH
                                )
                            )
                        } else {
                            noMoreResults = true
                            peopleLiveData.postValue(
                                ViewState(
                                    response.data?.peoples,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        peopleLiveData.postValue(
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
        getListPeople()
    }
}