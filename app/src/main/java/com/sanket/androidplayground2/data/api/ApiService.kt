package com.sanket.androidplayground2.data.api

import com.sanket.androidplayground2.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(ApiConstants.USERS)
    suspend fun getUsersResponse(): Response<List<User>>

    @GET(ApiConstants.USERS)
    suspend fun getUsers(): List<User>

}