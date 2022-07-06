package com.dhruv194.edvora.dataclasses


import com.google.gson.annotations.SerializedName

data class UserDataClass(
    val name: String,
    @SerializedName("station_code")
    val stationCode: Int,
    val url: String
)