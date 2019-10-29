package com.example.starwarsapi.domain

import com.example.starwarsapi.models.VehicleResponse
import com.example.starwarsapi.service.Result

interface ShowVehicleInteractor {

    fun getListVehicle(
        currentPage: Int,
        search: String = "",
        onResult: (Result<VehicleResponse?>) -> Unit
    )
}