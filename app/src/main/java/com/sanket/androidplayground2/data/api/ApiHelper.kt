package com.sanket.androidplayground2.data.api

import com.sanket.androidplayground2.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsersResponse(): Response<List<User>>

    suspend fun getUsers(): List<User>

}