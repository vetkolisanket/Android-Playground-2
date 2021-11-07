package com.sanket.androidplayground2.data.repository

import com.sanket.androidplayground2.data.api.ApiHelper
import com.sanket.androidplayground2.data.db.DatabaseHelper
import com.sanket.androidplayground2.data.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) {

    suspend fun getUsersResponse() = apiHelper.getUsersResponse()

    suspend fun getUsers() = apiHelper.getUsers()

    suspend fun getMoreUsers() = apiHelper.getMoreUsers()

    suspend fun getUsersFromDB() =  dbHelper.getUsers()

    suspend fun saveUsersToDB(users: List<User>) = dbHelper.insertAll(users)

}