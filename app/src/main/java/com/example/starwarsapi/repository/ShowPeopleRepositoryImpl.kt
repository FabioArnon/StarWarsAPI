package com.example.starwarsapi.repository

import com.example.starwarsapi.models.People
import com.example.starwarsapi.models.PeopleResponse
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.service.RetrofitInterface

class ShowPeopleRepositoryImpl(private val client: RetrofitInterface): ShowPeopleRepository{
    override suspend fun getListPeople(currentPage: Int): Result<PeopleResponse?> {
            val response = client.getpeople(currentPage)
            return if (response.isSuccessful) {
                Result.Success(response.body())
            } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }

    override suspend fun getPeopleId(id: List<String>): Result<List<People>> {
        val list = mutableListOf<People>()
        id.map {
            val response = client.getpeopleId(it)
            if (response.isSuccessful){
                response.body()?.let { people -> list.add(people) }
            }
        }
        return if(list.size == id.size){
            Result.Success(list)
        } else Result.Failure(Throwable("Erro"))
    }
}