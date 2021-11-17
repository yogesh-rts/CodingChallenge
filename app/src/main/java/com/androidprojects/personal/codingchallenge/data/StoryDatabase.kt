package com.androidprojects.personal.codingchallenge.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlin.reflect.KClass


/*
* DB class - an abstarct class layer for DB initialization
* */
@Database(entities = [UserStory::class], version = 1, exportSchema = false )
@TypeConverters(UserTypeConverters::class)
abstract class StoryDatabase : RoomDatabase(){

    abstract fun storyDao(): StoryDao
}