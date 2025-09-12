package com.ayub.khosa.myloginapplication.model
import com.google.gson.annotations.SerializedName

data class APIResponceUser(
    @SerializedName("response")
    val response: String,
    @SerializedName("data")
    val data: USER,
    @SerializedName("error")
    val error: String
)
