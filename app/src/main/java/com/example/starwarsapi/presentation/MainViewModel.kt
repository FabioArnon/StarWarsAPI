package com.example.starwarsapi.presentation

import androidx.lifecycle.MutableLiveData


class MainViewModel(
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {
    private val SelectLiveData = MutableLiveData<ViewState<String, ViewModelStatusEnum>>()

}