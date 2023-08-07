package com.desire.practical1.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.IdentityHashMap
@Parcelize
data class User(

   // https://fakestoreapi.com/products

    @SerializedName("title")
    var title : String = "",
    @SerializedName("category")
    var category : String = "",
    @SerializedName("image")
    var image : String = "",
    @SerializedName("id")
    var id: Int = 0,
):Parcelable
