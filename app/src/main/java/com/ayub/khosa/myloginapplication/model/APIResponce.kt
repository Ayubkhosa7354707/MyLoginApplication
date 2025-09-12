package com.ayub.khosa.myloginapplication.model

import com.google.gson.annotations.SerializedName

data class APIResponce(
    @SerializedName("response")
    val response: String,
    @SerializedName("data")
    val data: String,
    @SerializedName("error")
    val error: String
)