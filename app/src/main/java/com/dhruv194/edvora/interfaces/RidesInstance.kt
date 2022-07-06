package com.dhruv194.edvora.interfaces

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RidesInstance {
    val api: RideApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://assessment.api.vweb.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RideApi::class.java)
    }
}