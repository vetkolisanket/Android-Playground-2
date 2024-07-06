package com.sanket.androidplayground2.flow.data

import com.sanket.androidplayground2.flow.data.converter.UserDataConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserRepository(
    private val userDataConverter: UserDataConverter = UserDataConverter()
) {

    fun generateUserData(userData: List<Float>): Flow<DataProgressInfo> {
        val valuesForOnePercent = userData.size / 100
        return userDataConverter.convertUserData(userData).filter {
            it.convertedDataAmount % valuesForOnePercent == 0
        }.map {
            DataProgressInfo(it.convertedDataAmount/valuesForOnePercent)
        }.flowOn(Dispatchers.IO)
    }

}

data class DataProgressInfo(
    val progressPercentage: Int
)