package com.ayub.khosa.myloginapplication.model

import com.google.gson.annotations.SerializedName

data class APIResponceListPRODUCTS(
    @SerializedName("data")
    val `data`: ListPRODUCTS,
    @SerializedName("response")
    val response: String,
    @SerializedName("error")
    val error: String
)