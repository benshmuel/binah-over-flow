package com.example.binahoverflow.api

import com.example.binahoverflow.data.BinahAdapterItem
import com.example.binahoverflow.data.QuestionAdapterItem
import com.example.binahoverflow.data.QuestionsResponseClass
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeService {


    @GET("questions")
    suspend fun getQuestions(@Query("site") site: String = "stackoverflow")
            : QuestionsResponseClass


    companion object {
        private const val BASE_URL = "https://api.stackexchange.com/2.2/"

        fun create() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StackExchangeService::class.java)
    }
}