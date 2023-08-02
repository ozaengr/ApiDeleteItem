package com.desire.practical1.api

import com.google.gson.annotations.SerializedName

data class AddRequest(

    @SerializedName("title")
    var title : String = "",
    @SerializedName("category")
    var category : String = "",
    @SerializedName("image")
    var image : String = "",
    @SerializedName("id")
    var id: Int = 0,

)
