package com.example.spacex20.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.spacex20.feature_launches.core.common.Constants
import com.example.spacex20.feature_launches.data.datasource.local.LaunchDatabase
import com.example.spacex20.feature_launches.data.datasource.remote.LaunchApi
import com.example.spacex20.feature_launches.data.repository.LaunchRepositoryImpl
import com.example.spacex20.feature_launches.data.util.GsonParser
import com.example.spacex20.feature_launches.domain.repository.LaunchRepository
import com.example.spacex20.feature_launches.domain.use_case.get_launches.GetLaunchesUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideLaunchDatabase(app: Application): LaunchDatabase {
        return Room.databaseBuilder(
            app, LaunchDatabase::class.java, LaunchDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLaunchApi(): LaunchApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(LaunchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLaunchRepository(db: LaunchDatabase, api: LaunchApi): LaunchRepository {
        return LaunchRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideGetLaunchesUseCase(repository: LaunchRepository): GetLaunchesUseCase {
        return GetLaunchesUseCase(repository)
    }
}