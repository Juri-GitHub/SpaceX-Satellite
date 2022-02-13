package com.example.spacex20.feature_launches.data.datasource.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.spacex20.feature_launches.data.util.JsonParser
import com.example.spacex20.feature_launches.domain.model.Rocket
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromRocketJson(json: String): Rocket? {
        return jsonParser.fromJson<Rocket>(
            json,
            object : TypeToken<Rocket>(){}.type
        )
    }

    @TypeConverter
    fun toRocketJson(rocket: Rocket): String {
        return jsonParser.toJson(
            rocket,
            object : TypeToken<Rocket>(){}.type
        ) ?: ""
    }
}