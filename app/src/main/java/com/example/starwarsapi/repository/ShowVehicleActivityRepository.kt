package com.example.starwarsapi.repository

import com.example.starwarsapi.models.VehicleResponse
import com.example.starwarsapi.service.Result


interface ShowVehicleActivityRepository {
    suspend fun getListVehicles(currentPage: Int): Result<VehicleResponse?>
}