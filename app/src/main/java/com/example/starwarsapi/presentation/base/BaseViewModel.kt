package com.example.starwarsapi.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent

open class BaseViewModel(dispatcherProvider: DispatcherProvider) : ViewModel(), KoinComponent {
    var currentPage: Int = 1
    var search: String = ""
    var maxPage: Int = 1
    open var noMoreResults: Boolean = false
    open var error: Boolean = false
    private val viewModelJob = SupervisorJob()
    val scope = CoroutineScope(dispatcherProvider.io + viewModelJob)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    open fun nextPage() {
        if (error || noMoreResults || maxPage < currentPage) return
        currentPage++
    }
}
