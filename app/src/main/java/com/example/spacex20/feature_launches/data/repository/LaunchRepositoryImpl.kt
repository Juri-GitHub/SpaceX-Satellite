package com.example.spacex20.feature_launches.data.repository

import com.example.spacex20.feature_launches.core.util.Resource
import com.example.spacex20.feature_launches.data.datasource.local.LaunchDao
import com.example.spacex20.feature_launches.data.datasource.remote.LaunchApi
import com.example.spacex20.feature_launches.domain.model.Launch
import com.example.spacex20.feature_launches.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LaunchRepositoryImpl(
    private val api: LaunchApi,
    private val dao: LaunchDao
): LaunchRepository {

    override fun getLaunches(): Flow<Resource<List<Launch>>> = flow {
        emit(Resource.Loading())

        val launches = dao.getLaunches().map { it.toLaunch() }
        emit(Resource.Loading(data = launches))

        try {
            val remoteLaunches = api.getLaunches()
            dao.deleteLaunches()
            dao.insertLaunches(remoteLaunches.map { it.toLaunchEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Something went wrong",
                data = launches
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach to the server, check your internet connection",
                data = launches
            ))
        }

        val newLaunches = dao.getLaunches().map { it.toLaunch() }
        emit(Resource.Success(data = newLaunches))
    }
}