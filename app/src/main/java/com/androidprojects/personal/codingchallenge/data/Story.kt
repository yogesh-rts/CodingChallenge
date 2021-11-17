package com.androidprojects.personal.codingchallenge.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
*  @Story, a data-class is POJO class which maps the JSON response from API into JAVA object
*  @User, a data-class for the nested JSON object which contains author name, avatar and full name
* */

data class Story (
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "user") @Embedded var user: User,
    @ColumnInfo(name = "cover") val cover: String)