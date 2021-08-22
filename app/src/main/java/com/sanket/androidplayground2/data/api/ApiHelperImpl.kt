package com.sanket.androidplayground2.data.api

import com.sanket.androidplayground2.data.model.User
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsersResponse(): Response<List<User>> = apiService.getUsersResponse()
    override suspend fun getUsers(): List<User> = apiService.getUsers()
    override suspend fun getMoreUsers(): List<User> = apiService.getMoreUsers()
}