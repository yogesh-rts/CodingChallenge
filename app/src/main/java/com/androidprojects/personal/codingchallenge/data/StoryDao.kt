package com.androidprojects.personal.codingchallenge.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*
* @StoryDao - Data access object layer that interacts with the entities and the database
* @Insert - insertStoriesIntoDB() to store the fetched data from API for caching the data
* @Query - getStoriesfromDB() for fetching the stored data from DB in order to display when device goes offline
* @Query - deleteAllStoriesfromDB() to load new sets of data from the DB
*/
@Dao
interface StoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoriesIntoDB(stories:UserStory)

    @Query("SELECT * FROM stories")
    fun getStoriesfromDB(): Flow<UserStory>

    @Query("DELETE FROM stories")
    suspend fun deleteAllStoriesfromDB()

}