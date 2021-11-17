package com.androidprojects.personal.codingchallenge.api


import com.androidprojects.personal.codingchallenge.data.UserStory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/*
* Using Companion object, maintaining the singleton pattern for the URL
* @GET request fetches all the stories from the API response (FOR NOW)
* */
interface ApiRequest {

    companion object{
        const val BASE_URL = "https://www.wattpad.com/api/v3/"
    }

    /*retrieving the data by passing query parameters
    * @Offset and @Limit are the query parameters for filtering the data
    * */
    @GET("stories")
    suspend fun getStories(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
      //  @Query("fields") story: UserStory
    ): UserStory

    // retrieving the data by passing URL as an arugment
    @GET
    suspend fun getStories(
        @Url url: String
    ): UserStory
}