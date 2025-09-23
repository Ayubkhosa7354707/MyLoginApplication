package com.ayub.khosa.myloginapplication.model

import com.google.gson.annotations.SerializedName

data class APIResponceListCATEGORYS(
    @SerializedName("data")
    val `data`: ListCATEGORYS,
    @SerializedName("response")
    val response: String,
    @SerializedName("error")
    val error: String
)