package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.User
import com.example.code.data.source.remote.UserService

class DefaultUserRepository(
    private val localDataSource: AppDatabase,
    private val remoteDataSource: UserService
) {
    suspend fun getAllUsers(forceUpdate: Boolean = false): List<User> {
        return if (forceUpdate) {
            val users = remoteDataSource.getAllUsers()
            localDataSource.userDao().deleteAllUsers()
            for (user in users) {
                localDataSource.userDao().insertUser(user)
            }
            users
        } else {
            localDataSource.userDao().getAllUsers()
        }
    }

    suspend fun getUserById(userId: String): User? {
        localDataSource.userDao().deleteAllUsers()
        val users = remoteDataSource.getAllUsers()
        localDataSource.userDao().deleteAllUsers()
        for (user in users) {
            localDataSource.userDao().insertUser(user)
        }
        return localDataSource.userDao().getUserById(userId)
    }

    suspend fun getUserByIdAndPassword(username: String, password: String): User? {
        println("username : ${username}")
        println("password : ${password}")
        println("users : ${remoteDataSource.getUserByIdAndPassword(username,password)}")
        return remoteDataSource.getUserByIdAndPassword(username,password)
    }

    suspend fun createUser(user: User) {
        val newUser = remoteDataSource.createUser(user)
        localDataSource.userDao().insertUser(newUser)
    }

    suspend fun updateUser(user: User) {
        val newUser = remoteDataSource.updateUser(user.userId, user)
        localDataSource.userDao().insertUser(newUser)
    }

    suspend fun deleteUser(user: User) {
        remoteDataSource.deleteUser(user.userId)
        localDataSource.userDao().deleteUser(user.userId)
    }
}
