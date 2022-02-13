package com.example.spacex20.feature_launches.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spacex20.feature_launches.domain.model.Launch
import com.example.spacex20.feature_launches.domain.model.Rocket

@Entity
data class LaunchEntity(
    val flight_number: Int,
    val mission_name: String,
    val launch_year: String,
    val launch_success: Boolean? = null,
    @PrimaryKey val id: Int? = null
) {
    fun toLaunch(): Launch {
        return Launch(
            flight_number = flight_number,
            mission_name = mission_name,
            launch_year = launch_year,
            launch_success = launch_success
        )
    }
}
