package com.example.starwarsapi.application

import com.example.starwarsapi.service.Result
import retrofit2.Response
import java.lang.Exception
import kotlin.math.ceil
import kotlin.math.roundToInt

suspend fun <T> safeAppCall(call: suspend () -> Response<T>): Result<T> {
    return try{
        val response = call()
        if(response.isSuccessful){
            Result.Success(response.body()!!)
        } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    }catch (e: Exception){
        Result.Failure(e)
    }
}

fun pages(count: Int): Int{
    return ceil(count.div(10.0)).roundToInt()
}