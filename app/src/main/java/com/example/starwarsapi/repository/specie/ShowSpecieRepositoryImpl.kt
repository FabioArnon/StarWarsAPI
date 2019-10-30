package com.example.starwarsapi.repository.specie

import com.example.starwarsapi.application.xt.safeAppCall
import com.example.starwarsapi.models.specie.SpecieResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowSpecieRepositoryImpl(private val client: RetrofitInterface) :
    ShowSpecieRepository {
    override suspend fun getListSpecies(currentPage: Int, search: String): Result<SpecieResponse?> =
        safeAppCall {
            client.getspecies(currentPage, search)
        }
}