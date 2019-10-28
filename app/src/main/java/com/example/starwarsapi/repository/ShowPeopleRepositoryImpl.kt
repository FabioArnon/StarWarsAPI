package com.example.starwarsapi.repository

import com.example.starwarsapi.application.safeAppCall
import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.PeopleResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowPeopleRepositoryImpl(private val client: RetrofitInterface): ShowPeopleRepository{
    override suspend fun getListPeople(currentPage: Int): Result<PeopleResponse?> = safeAppCall {
        client.getpeople(currentPage, "")
    }


    override suspend fun getSearchPeople(currentPage: Int,search: String): Result<PeopleResponse?> = safeAppCall {
        client.getpeople(currentPage, search)
    }

    override suspend fun getPeopleId(id: List<String>): Result<List<People>> {
        val list = mutableListOf<People>()
        id.map {
            when(val response = safeAppCall{client.getpeopleId(it)}){
                is Result.Success -> list.add(response.data)
                is Result.Failure -> {}
            }
        }
        return if(list.size == id.size){
            Result.Success(list)
        } else Result.Failure(Throwable("Erro"))
    }
}