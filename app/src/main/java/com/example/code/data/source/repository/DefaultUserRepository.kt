package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.User
import com.example.code.data.source.remote.UserService
import com.example.code.data.utils.UserUpdateRequest
import java.security.PrivateKey

class DefaultUserRepository(
    private val localDataSource: AppDatabase,
    private val remoteDataSource: UserService
) {
    suspend fun getAllUsers(forceUpdate: Boolean = false): List<User> {
        return if (forceUpdate) {
            val users = remoteDataSource.getAllUsers()
            localDataSource.userDao().deleteAllUsers()
            for (user in users) {
                localDataSource.userDao().insertUser(User(
                    userId = user.userId,
                    userName = user.userName,
                    userUsername = user.userUsername,
                    userPassword = user.userPassword,
                    userEmail = user.userEmail,
                    userPhone = user.userPhone,
                    companyId = user.companyId,
                    userRole = user.userRole
                ))
            }
            users
        } else {
            localDataSource.userDao().getAllUsers()
        }
    }

    suspend fun getUserById(userId: String): User {
        try {
            val users = remoteDataSource.getAllUsers()
            println("Guk")
//            localDataSource.userDao().deleteAllUsers()
//            println("Guk2")
//            for (user in users) {
//                println("GUK GUK GUK RAWRRRRR")
//                localDataSource.userDao().insertUser(User(
//                    userId = user.userId,
//                    userName = user.userName,
//                    userUsername = user.userUsername,
//                    userPassword = user.userPassword,
//                    userEmail = user.userEmail,
//                    userPhone = user.userPhone,
//                    companyId = user.companyId,
//                    userRole = user.userRole
//                ))
//            }
            println("Debug 5")
            println(userId)
            val user = remoteDataSource.getUserById(userId)
            println("Debug 6")
            return User(
                userId = user.userId,
                userName = user.userName,
                userUsername = user.userUsername,
                userPassword = user.userPassword,
                userEmail = user.userEmail,
                userPhone = user.userPhone,
                companyId = user.companyId,
                userRole = user.userRole
            )
        }catch (e: Exception) {
            println("getUserById")
            e.printStackTrace()
        }
        return TODO("Provide the return value")
    }

    suspend fun getUserByIdAndPassword(username: String, password: String): User? {
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

    suspend fun updateUser(userId: String, userUsername: String, userEmail: String, userPhone: String): User {
        val userUpdateRequest = UserUpdateRequest(
            username = userUsername,
            email = userEmail,
            phone = userPhone
        )
        println("userUpdateRequest: $userUpdateRequest")
        return remoteDataSource.updateUser(userId, userUpdateRequest)
    }

//    suspend fun updateUserRole(user: User) {
////        val newUser = remoteDataSource.updateUser(user.userId, user)
////        localDataSource.userDao().insertUser(newUser)
//    }

    suspend fun promoteUser(userId: String):User?{
        try {
            val user = remoteDataSource.promoteToManager(userId)
            return localDataSource.userDao().getUserById(user!!.userId)
        }catch (e: Exception) {
            println("promoteUser")
            e.printStackTrace()
        }
        return TODO("Provide the return value")

    }

    suspend fun deleteUser(user: User) {
        remoteDataSource.deleteUser(user.userId)
        localDataSource.userDao().deleteUser(user.userId)
    }

    suspend fun joinToCompany(userId:String, privateKey: String){
        remoteDataSource.joinToCompany(userId, privateKey)
    }


}
