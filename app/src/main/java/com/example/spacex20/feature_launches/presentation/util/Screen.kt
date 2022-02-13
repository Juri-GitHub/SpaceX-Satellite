package com.example.spacex20.feature_launches.presentation.util

sealed class Screen(val route: String) {
    object LaunchListScreen : Screen("launch_list_screen")
    object LaunchDetailScreen: Screen("launch_detail_screen")
}