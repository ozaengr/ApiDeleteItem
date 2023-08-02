package com.desire.practical1.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Api {

    // https://fakestoreapi.com/products
    @GET("products")
    fun getData() : Call<ArrayList<User>>

    @DELETE("products/{id}")
    fun deleteData(@Path("id") id : Int) : Call<Unit>

    @POST("products")
    fun addItem(@Body addRequest: AddRequest) : Call<AddResponse>

}