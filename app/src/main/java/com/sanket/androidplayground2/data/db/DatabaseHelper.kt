package com.sanket.androidplayground2.data.db

import com.sanket.androidplayground2.data.model.User

interface DatabaseHelper {

    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)

}