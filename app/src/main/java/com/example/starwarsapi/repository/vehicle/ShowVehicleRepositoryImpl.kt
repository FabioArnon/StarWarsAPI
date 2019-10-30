package com.example.starwarsapi.repository.vehicle

import com.example.starwarsapi.application.xt.safeAppCall
import com.example.starwarsapi.models.vehicle.VehicleResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowVehicleRepositoryImpl(private val client: RetrofitInterface):
    ShowVehicleRepository {
    override suspend fun getListVehicles(currentPage: Int, search: String): Result<VehicleResponse?> =
        safeAppCall {
            client.getvehicles(currentPage, search)
        }

}