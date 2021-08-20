package com.sanket.androidplayground2.coroutines

import com.sanket.androidplayground2.data.model.User
import com.sanket.androidplayground2.hilt.UserAdapter

class ApiUserAdapter(val users: MutableList<User>): UserAdapter(users) {
}