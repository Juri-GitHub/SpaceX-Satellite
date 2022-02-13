package com.example.spacex20.feature_launches.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.spacex20.feature_launches.data.datasource.local.entity.LaunchEntity

@Database(
    entities = [LaunchEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class LaunchDatabase: RoomDatabase() {

    abstract val dao: LaunchDao

    companion object {
        const val DATABASE_NAME = "launch_db"
    }
}
