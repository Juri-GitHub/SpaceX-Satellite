package com.example.spacex20.feature_launches.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacex20.feature_launches.presentation.launch_list.LaunchListScreen
import com.example.spacex20.feature_launches.presentation.util.Screen
import com.example.spacex20.ui.theme.SpaceXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LaunchListScreen.route
                    ) {
                        composable(
                            route = Screen.LaunchListScreen.route
                        ) {
                            LaunchListScreen(navController)
                        }
                    }
                }
            }
        }
    }
}