package com.androidprojects.personal.codingchallenge.repository


import androidx.room.withTransaction
import com.androidprojects.personal.codingchallenge.api.ApiRequest
import com.androidprojects.personal.codingchallenge.data.StoryDatabase
import com.androidprojects.personal.codingchallenge.util.networkBoundResource
import javax.inject.Inject

class StoryRepo @Inject constructor(
    private val api: ApiRequest,
    private val db: StoryDatabase
) {
    private val storyDaoLayer = db.storyDao()
    private val dataUrl: String? = "https://www.wattpad.com/api/v3/stories?offset=0&limit=10&fields=stories(id,title,cover,user)&filter=new"



    fun getStoriesList() = networkBoundResource(
        query = {
            storyDaoLayer.getStoriesfromDB()
        },
        fetch = {

            api.getStories(dataUrl!!)
        },
        saveFetchResult = {
            db.withTransaction {
                storyDaoLayer.deleteAllStoriesfromDB()
                storyDaoLayer.insertStoriesIntoDB(it)
            }


        }
    )


}