package com.example.spacex20.feature_launches.domain.repository

import com.example.spacex20.feature_launches.core.util.Resource
import com.example.spacex20.feature_launches.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {

    fun getLaunches(): Flow<Resource<List<Launch>>>
}