package com.sanket.androidplayground2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "USERS")
data class User (

    @Json(name = "id")
    @PrimaryKey
    val id: Int = 0,

    @Json(name = "name")
    @ColumnInfo(name = "name")
    val name: String = "",

    @Json(name = "email")
    @ColumnInfo(name = "email")
    val email: String = "",

    @Json(name = "avatar")
    @ColumnInfo(name = "avatar")
    val avatar: String = ""
)