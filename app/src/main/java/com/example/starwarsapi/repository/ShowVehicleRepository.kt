package com.example.starwarsapi.repository

import com.example.starwarsapi.models.VehicleResponse
import com.example.starwarsapi.service.Result


interface ShowVehicleRepository {
    suspend fun getListVehicles(currentPage: Int): Result<VehicleResponse?>
    suspend fun searchListVehicles(search: String): Result<VehicleResponse?>
}
