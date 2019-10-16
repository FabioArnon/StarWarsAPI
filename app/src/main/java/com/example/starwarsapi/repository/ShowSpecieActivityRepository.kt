package com.example.starwarsapi.repository

import com.example.starwarsapi.models.*
import com.example.starwarsapi.service.Result


interface ShowSpecieActivityRepository {
    suspend fun getListSpecies(currentPage: Int): Result<SpecieResponse?>
}