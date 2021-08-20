package com.sanket.androidplayground2.data.repository

import com.sanket.androidplayground2.data.api.ApiHelper
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsersResponse() = apiHelper.getUsersResponse()

    suspend fun getUsers() = apiHelper.getUsers()

}