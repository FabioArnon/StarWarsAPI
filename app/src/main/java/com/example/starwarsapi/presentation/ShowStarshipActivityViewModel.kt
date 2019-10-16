package com.example.starwarsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.models.Starships
import com.example.starwarsapi.presentation.ViewModelStatusEnum.*
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.repository.ShowStarshipActivityRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowStarshipActivityViewModel(
    private val repository: ShowStarshipActivityRepository,
    private val dispatcherProvider: DispatcherProvider
): BaseViewModel(dispatcherProvider) {
    private val starshipLiveData = MutableLiveData<ViewState<List<Starships>, ViewModelStatusEnum>>()
    fun getList(): LiveData<ViewState<List<Starships>, ViewModelStatusEnum>> = starshipLiveData

    fun getListStarship(){
        starshipLiveData.postValue(ViewState(status = LOADING))
        scope.launch(dispatcherProvider.io){
            val response = repository.getListStarships(currentPage)
            withContext(dispatcherProvider.ui){
                when(response){
                    is Result.Success -> {
                        if(!response.data?.starships.isNullOrEmpty()){
                            starshipLiveData.postValue(
                                ViewState(
                                    response.data?.starships,
                                    SUCCESS
                                )
                            )
                        } else{
                            noMoreResults = true
                            starshipLiveData.postValue(
                                ViewState(
                                    response.data?.starships,
                                    ERROR_LIST_EMPTY
                                )
                            )
                        }
                    }
                    is Result.Failure->{
                        error = true
                        starshipLiveData.postValue(
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
        getListStarship()
    }
}
