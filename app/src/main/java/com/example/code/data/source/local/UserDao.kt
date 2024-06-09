package com.example.code.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.code.data.source.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE user_id = :userId")
    suspend fun getUserById(userId: String): User?

    @Query("SELECT * FROM users WHERE user_username = :user_username AND user_password = :password")
    suspend fun getUserByIdAndPassword(user_username: String, password: String): User?

    @Insert()
    suspend fun insertUser(user: User)

    @Query("UPDATE users SET user_role = :newRole WHERE user_id = :userId")
    suspend fun updateUserRole(userId: String, newRole: String)

    @Query("DELETE FROM users WHERE user_id = :userId")
    suspend fun deleteUser(userId: kotlin.String?)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}
