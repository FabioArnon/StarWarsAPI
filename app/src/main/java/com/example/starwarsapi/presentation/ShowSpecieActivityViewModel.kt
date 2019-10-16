package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.models.Species
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.repository.ShowSpecieActivityRepository
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ShowSpecieActivityViewModel(
    private val repository: ShowSpecieActivityRepository,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val specieLiveData = MutableLiveData<ViewState<List<Species>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Species>, ViewModelStatusEnum>> = specieLiveData

    fun getListSpecies() {
        specieLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListSpecies(currentPage)
            withContext(dispatcherProvider.ui) {
                when (response) {
                    is Result.Success -> {
                        if (!response.data?.species.isNullOrEmpty()) {
                            specieLiveData.postValue(
                                ViewState(
                                    response.data?.species,
                                    SUCCESS
                                )
                            )
                        } else {
                            noMoreResults = true
                            specieLiveData.postValue(
                                ViewState(
                                    response.data?.species,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        error = true
                        specieLiveData.postValue(
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
        getListSpecies()
    }
}