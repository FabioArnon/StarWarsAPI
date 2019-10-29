package com.example.starwarsapi.domain

import com.example.starwarsapi.models.Films
import com.example.starwarsapi.service.Result

interface BaseFilmInteractor{

    fun getFilmId(url: List<String>, onResult: (Result<List<Films>>) -> Unit)
}