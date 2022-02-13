package com.example.spacex20.feature_launches.presentation.launch_list

import com.example.spacex20.feature_launches.domain.model.Launch

data class LaunchListState(
    val launches: List<Launch> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)