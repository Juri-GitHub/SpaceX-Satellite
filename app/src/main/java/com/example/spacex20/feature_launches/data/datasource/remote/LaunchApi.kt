package com.example.spacex20.feature_launches.data.datasource.remote

import com.example.spacex20.feature_launches.data.datasource.remote.dto.LaunchDto
import retrofit2.http.GET
import retrofit2.http.Path

interface LaunchApi {

    @GET("/v3/launches")
    suspend fun getLaunches(): List<LaunchDto>

    @GET("/v3/launches/{launchId}")
    suspend fun getLaunchById(@Path("launchId") launchId: String): LaunchDto
}