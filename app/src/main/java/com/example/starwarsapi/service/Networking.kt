package com.example.starwarsapi.service

import com.example.starwarsapi.application.xt.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Networking {
    companion object{
        private lateinit var service: RetrofitInterface
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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
            okHttp.writeTimeout(15,TimeUnit.SECONDS)
            okHttp.readTimeout(15,TimeUnit.SECONDS)
            okHttp.connectTimeout(15,TimeUnit.SECONDS)
            logging.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttp.addInterceptor(logging)
            return okHttp
            }
    }


}