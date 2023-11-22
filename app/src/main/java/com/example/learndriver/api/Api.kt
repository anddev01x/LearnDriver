package com.example.learndriver.api

import com.example.learndriver.model.ResponseListQuestion
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface Api {
    companion object {
        private val gson: Gson = GsonBuilder().setDateFormat("dd/MM/yyyy").create()
        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        private val okBuilder = OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS).retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)

        val apiService: Api = Retrofit.Builder().baseUrl("https://thantrieu.com/")
            .addConverterFactory(GsonConverterFactory.create(gson)).client(okBuilder.build())
            .build().create(Api::class.java)
    }

    @GET("resources/braniumapis/driver_test.json")
    suspend fun getListQuestion(): Response<ResponseListQuestion>

}