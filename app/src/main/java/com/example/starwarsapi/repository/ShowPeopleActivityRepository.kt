package com.example.starwarsapi.repository

import com.example.starwarsapi.models.*
import com.example.starwarsapi.service.Result


interface ShowPeopleActivityRepository {
    suspend fun getListPeople(currentPage: Int): Result<PeopleResponse?>
}