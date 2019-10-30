package com.example.starwarsapi.repository.vehicle

import com.example.starwarsapi.models.vehicle.VehicleResponse
import com.example.starwarsapi.service.Result


interface ShowVehicleRepository {
    suspend fun getListVehicles(currentPage: Int, search: String): Result<VehicleResponse?>
}
