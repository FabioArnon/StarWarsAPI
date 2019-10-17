package com.example.starwarsapi.repository

import com.example.starwarsapi.models.VehicleResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowVehicleRepositoryImpl(private val client: RetrofitInterface): ShowVehicleRepository{
    override suspend fun getListVehicles(currentPage: Int): Result<VehicleResponse?> {
        val response = client.getvehicles(currentPage)
        return if(response.isSuccessful){
            Result.Success(response.body())
        }else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }
}