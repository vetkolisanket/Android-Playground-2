package com.sanket.androidplayground2.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sanket.androidplayground2.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM USERS")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insertAll(users: List<User>)

    @Delete
    suspend fun delete(user: User)

}