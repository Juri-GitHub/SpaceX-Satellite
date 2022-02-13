package com.example.spacex20.feature_launches.domain.model

data class Launch(
    val flight_number: Int,
    val mission_name: String,
    val launch_year: String,
    val launch_success: Boolean? = null
)
