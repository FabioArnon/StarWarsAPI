package com.example.starwarsapi.repository

import com.example.starwarsapi.application.safeAppCall
import com.example.starwarsapi.models.SpecieResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowSpecieRepositoryImpl(private val client: RetrofitInterface): ShowSpecieRepository{
    override suspend fun getListSpecies(currentPage: Int): Result<SpecieResponse?> = safeAppCall{
        client.getspecies(currentPage, "")
    }

    override suspend fun searchListSpecies(currentPage: Int, search: String): Result<SpecieResponse?> = safeAppCall{
        client.getspecies(currentPage, search)
    }
}