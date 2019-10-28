package com.example.starwarsapi.repository

import com.example.starwarsapi.models.*
import com.example.starwarsapi.service.Result


interface ShowPeopleRepository {
    suspend fun getListPeople(currentPage: Int): Result<PeopleResponse?>
    suspend fun getSearchPeople(currentPage: Int, search: String): Result<PeopleResponse?>
    suspend fun getPeopleId(id: List<String>): Result<List<People>>

}