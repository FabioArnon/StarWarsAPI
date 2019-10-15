package com.example.starwarsapi.presentation

class ViewState<T, S>(
    val data: T? = null,
    val status: S,
    val error: Throwable? = null
)