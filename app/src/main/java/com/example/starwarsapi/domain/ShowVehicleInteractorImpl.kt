package com.example.starwarsapi.domain

import com.example.starwarsapi.models.VehicleResponse
import com.example.starwarsapi.presentation.DispatcherProvider
import com.example.starwarsapi.repository.ShowVehicleRepository
import com.example.starwarsapi.service.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowVehicleInteractorImpl(
    private val repository: ShowVehicleRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val scope: CoroutineScope
) : ShowVehicleInteractor {
    override fun getListVehicle(
        currentPage: Int,
        search: String,
        onResult: (Result<VehicleResponse?>) -> Unit
    ) {
        scope.launch(dispatcherProvider.io) {
            val response = repository.getListVehicles(currentPage, search)
            withContext(dispatcherProvider.ui) {
                onResult(response)
            }
        }
    }
}