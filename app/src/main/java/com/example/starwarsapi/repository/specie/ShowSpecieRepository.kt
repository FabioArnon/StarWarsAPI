package com.example.starwarsapi.repository.specie

import com.example.starwarsapi.models.specie.SpecieResponse
import com.example.starwarsapi.service.Result


interface ShowSpecieRepository {
    suspend fun getListSpecies(currentPage: Int, search: String): Result<SpecieResponse?>
}