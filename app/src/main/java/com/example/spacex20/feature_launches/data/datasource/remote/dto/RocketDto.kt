package com.example.spacex20.feature_launches.data.datasource.remote.dto

import com.example.spacex20.feature_launches.domain.model.Rocket

data class RocketDto(
    val rocket_id: String,
    val rocket_name: String,
    val rocket_type: String
) {
    fun toRocket(): Rocket {
        return Rocket(
            rocket_id = rocket_id,
            rocket_name = rocket_name,
            rocket_type = rocket_type
        )
    }
}