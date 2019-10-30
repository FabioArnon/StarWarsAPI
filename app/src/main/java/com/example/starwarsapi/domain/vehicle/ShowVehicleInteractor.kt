package com.example.starwarsapi.domain.vehicle

import com.example.starwarsapi.models.vehicle.VehicleResponse
import com.example.starwarsapi.service.Result

interface ShowVehicleInteractor {

    fun getListVehicle(
        currentPage: Int,
        search: String = "",
        onResult: (Result<VehicleResponse?>) -> Unit
    )
}