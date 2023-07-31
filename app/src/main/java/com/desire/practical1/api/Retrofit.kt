package com.desire.practical1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Retrofit {


    val baseUrl = "https://fakestoreapi.com/"


    var retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()


    var api = retrofit.create(Api::class.java)
}