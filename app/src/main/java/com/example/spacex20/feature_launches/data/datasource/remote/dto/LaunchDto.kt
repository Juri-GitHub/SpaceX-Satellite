package com.example.spacex20.feature_launches.data.datasource.remote.dto

import com.example.spacex20.feature_launches.data.datasource.local.entity.LaunchEntity

data class LaunchDto(
    val flight_number: Int,
    val mission_name: String,
    val launch_year: String,
    val launch_success: Boolean? = null,
    val rocket: RocketDto,
    val details: String? = ""
) {
    fun toLaunchEntity(): LaunchEntity {
        return LaunchEntity(
            flight_number = flight_number,
            mission_name = mission_name,
            launch_year = launch_year,
            launch_success = launch_success
        )
    }
}
