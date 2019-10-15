package com.example.starwarsapi.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Networking {
    companion object{
        private lateinit var service: RetrofitInterface
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp().build())
            .build()

        fun retrofitInterface(): RetrofitInterface {
            return if (::service.isInitialized) {
                service
            } else {
                service = retrofit.create(RetrofitInterface::class.java)
                service
            }
        }

        fun getOkHttp(): OkHttpClient.Builder {
            val okHttp = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttp.addInterceptor(logging)
            return okHttp
            }
    }


}