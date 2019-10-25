package com.example.starwarsapi.repository

import com.example.starwarsapi.models.VehicleResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowVehicleRepositoryImpl(private val client: RetrofitInterface): ShowVehicleRepository{
    override suspend fun getListVehicles(currentPage: Int): Result<VehicleResponse?> {
        val response = client.getvehicles(currentPage, "")
        return if(response.isSuccessful){
            Result.Success(response.body())
        }else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }

    override suspend fun searchListVehicles(search: String): Result<VehicleResponse?> {
        val response = client.getvehicles(1, search)
        return if(response.isSuccessful){
            Result.Success(response.body())
        }else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }
}