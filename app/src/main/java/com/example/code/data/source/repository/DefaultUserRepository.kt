package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.User
import com.example.code.data.source.remote.UserService

class DefaultUserRepository(
    private val localDataSource: AppDatabase,
    private val remoteDataSource: UserService
) {
    suspend fun getAllUsers(forceUpdate: Boolean = false): List<User> {
        println("tesssss")
        return if (forceUpdate) {
            val users = remoteDataSource.getAllUsers()
            println("User dari remote : ${users}")
            localDataSource.userDao().deleteAllUsers()
            for (user in users) {
                localDataSource.userDao().insertUser(User(
                    userId = user.userId,
                    userName = user.userName,
                    userUsername = user.userUsername,
                    userPassword = user.userPassword,
                    userEmail = user.userEmail,
                    userPhone = user.userPhone,
                    userRole = user.userRole
                ))
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

    suspend fun createUser(
        userName: String,
        userUsername: String,
        userPassword: String?,
        userConfirmationPassword: String,
        userPhone: String,
        userEmail: String,
        userRole: String
    ) {
        val newUser = remoteDataSource.createUser(
            userName,
            userUsername,
            userPassword,
            userConfirmationPassword,
            userPhone,
            userEmail,
            userRole
        )

        val user = User(
            userId = newUser.userId,
            userName = newUser.userName,
            userUsername = newUser.userUsername,
            userPassword = newUser.userPassword,
            userEmail = newUser.userEmail,
            userPhone = newUser.userPhone,
            userRole = newUser.userRole
            )

        localDataSource.userDao().insertUser(user)
    }

    suspend fun updateUser(userId: String, userUsername: String, userEmail: String, userPhone: String){
        remoteDataSource.updateUser(userId, userUsername, userPhone, userEmail)
    }

    suspend fun updateUserRole(user: User) {
//        val newUser = remoteDataSource.updateUser(user.userId, user)
//        localDataSource.userDao().insertUser(newUser)
    }

    suspend fun deleteUser(user: User) {
        remoteDataSource.deleteUser(user.userId)
        localDataSource.userDao().deleteUser(user.userId)
    }
}
