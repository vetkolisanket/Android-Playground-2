package com.sanket.androidplayground2.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.androidplayground2.commons.utils.NetworkHelper
import com.sanket.androidplayground2.commons.utils.Resource
import com.sanket.androidplayground2.data.model.User
import com.sanket.androidplayground2.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class CoroutinesViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){

    private val users = MutableLiveData<Resource<List<User>>>()
    fun getUsers(): LiveData<Resource<List<User>>> = users

    fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading())
            try {
                val usersFromApi = userRepository.getUsers()
                users.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error(e.toString()))
            }
        }
    }

    fun fetchUsersSerially() {
        viewModelScope.launch {
            users.postValue(Resource.loading())
            try {
                val usersFromApi = userRepository.getUsers()
                val moreUsersFromApi = userRepository.getMoreUsers()
                val allUsersFromApi = mutableListOf<User>()
                allUsersFromApi.addAll(usersFromApi)
                allUsersFromApi.addAll(moreUsersFromApi)
                users.postValue(Resource.success(allUsersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error(e.toString()))
            }
        }
    }

    fun fetchUsersParallely() {
        viewModelScope.launch {
            try {
                //coroutine scope is needed, else in case of any network error it will crash
                coroutineScope {
                    val usersFromApiDeferred = async { userRepository.getUsers() }
                    val moreUsersFromApiDeferred = async { userRepository.getMoreUsers() }

                    val usersFromApi = usersFromApiDeferred.await()
                    val moreUsersFromApi = moreUsersFromApiDeferred.await()

                    val allUsersFromApi = mutableListOf<User>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)

                    users.postValue(Resource.success(allUsersFromApi))
                }
            } catch (e: Exception) {
                users.postValue(Resource.error(e.localizedMessage ?: e.toString()))
            }
        }
    }

}