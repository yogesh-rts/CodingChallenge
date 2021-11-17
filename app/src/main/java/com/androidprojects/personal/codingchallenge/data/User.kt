package com.androidprojects.personal.codingchallenge.data

import androidx.room.ColumnInfo
import androidx.room.Ignore

data class User (
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "avatar") var avatar: String?,
    @ColumnInfo(name = "fullname") var fullName: String?
)
