package com.example.spacex20.feature_launches.domain.use_case.get_launches

import com.example.spacex20.feature_launches.core.util.Resource
import com.example.spacex20.feature_launches.domain.model.Launch
import com.example.spacex20.feature_launches.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow

class GetLaunchesUseCase(
    private val repository: LaunchRepository
) {

    operator fun invoke(): Flow<Resource<List<Launch>>> {
        return repository.getLaunches()
    }
}