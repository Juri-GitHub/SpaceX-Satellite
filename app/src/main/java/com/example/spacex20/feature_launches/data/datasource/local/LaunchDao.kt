package com.example.spacex20.feature_launches.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacex20.feature_launches.data.datasource.local.entity.LaunchEntity

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launches: List<LaunchEntity>)

    @Query("DELETE FROM LaunchEntity")
    suspend fun deleteLaunches()

    @Query("SELECT * FROM launchEntity")
    suspend fun getLaunches(): List<LaunchEntity>
}