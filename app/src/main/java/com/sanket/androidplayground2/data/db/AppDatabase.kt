package com.sanket.androidplayground2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanket.androidplayground2.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}