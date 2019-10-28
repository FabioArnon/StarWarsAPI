package com.example.starwarsapi.repository

import com.example.starwarsapi.application.safeAppCall
import com.example.starwarsapi.models.VehicleResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowVehicleRepositoryImpl(private val client: RetrofitInterface): ShowVehicleRepository{
    override suspend fun getListVehicles(currentPage: Int): Result<VehicleResponse?> = safeAppCall{
        client.getvehicles(currentPage, "")
    }

    override suspend fun searchListVehicles(currentPage: Int, search: String): Result<VehicleResponse?> = safeAppCall{
        client.getvehicles(currentPage, search)
    }
}