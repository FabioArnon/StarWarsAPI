package com.example.starwarsapi.repository

import com.example.starwarsapi.models.*
import com.example.starwarsapi.service.Result


interface ShowSpecieRepository {
    suspend fun getListSpecies(currentPage: Int): Result<SpecieResponse?>
}