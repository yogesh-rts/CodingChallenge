package com.androidprojects.personal.codingchallenge.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stories")
data class UserStory(
    @SerializedName("stories") val userStory: List<Story>,
    @SerializedName("nextUrl") val nextUrl: String,
    @PrimaryKey(autoGenerate = true) val id: Int?
)
