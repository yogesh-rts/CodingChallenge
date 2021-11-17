package com.androidprojects.personal.codingchallenge.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
* Created @UserTypeConverter class for storing the custom data (Story class) objects into single column database in Room
*
* @fromUserObjectToString() used to convert to string value in order to flattened the DB structure
* @fromStringToUserObject used to convert to list type while retrieving data from database in JSON format
*
* */
class UserTypeConverters {

    @TypeConverter
    fun fromUserObjectToString(value: List<Story>): String?{
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringToUserObject(value: String): List<Story>{
        val gson = Gson()
        val type = object : TypeToken<List<Story>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromUser(value: User): String? =  Gson().toJson(value)

    @TypeConverter
    fun fromString(value: String): User? = Gson().fromJson(value, User::class.java)
}