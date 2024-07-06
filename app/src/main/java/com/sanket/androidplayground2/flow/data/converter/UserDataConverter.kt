package com.sanket.androidplayground2.flow.data.converter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.pow

class UserDataConverter {

    fun convertUserData(userData: List<Float>): Flow<DataConvertingInfo> = flow {
        var alreadyConvertedValues = 0
        userData.forEach {
            for (i in 0..1000) {
                i.toDouble().pow(2)
            }
            emit(DataConvertingInfo(++alreadyConvertedValues))
        }
    }

}

data class DataConvertingInfo(
    val convertedDataAmount: Int
)