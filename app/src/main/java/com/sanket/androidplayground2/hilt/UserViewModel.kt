package com.sanket.androidplayground2.hilt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.androidplayground2.commons.utils.NetworkHelper
import com.sanket.androidplayground2.commons.utils.Resource
import com.sanket.androidplayground2.data.model.User
import com.sanket.androidplayground2.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()

    fun getUsers(): LiveData<Resource<List<User>>> = _users

    init { fetchUsers() }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading())
            if (networkHelper.isNetworkConnected()) {
                userRepository.getUsers().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else _users.postValue(Resource.error("No internet connection"))
                }
            }
        }
    }

}