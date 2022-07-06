package com.dhruv194.edvora.interfaces

import com.dhruv194.edvora.dataclasses.RidesDataClass
import com.dhruv194.edvora.dataclasses.UserDataClass
import retrofit2.Response
import retrofit2.http.GET

interface RideApi {

    @GET("/rides")
    suspend fun getRide(): Response<List<RidesDataClass>>

    @GET("/user")
    suspend fun getUser():Response<UserDataClass>
}