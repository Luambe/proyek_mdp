package com.example.code.data.source.remote

import com.example.code.data.source.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("api/v1/user")
    suspend fun getAllUsers(): List<User>

    @GET("api/v1/user/{user_id}")
    suspend fun getUserById(@Path("user_id") userId: String): User

    @GET("api/v1/user/{user_id}/{user_password}")
    suspend fun getUserByIdAndPassword(@Path("user_id") userId: String, @Path("user_password") password: String): User

    @POST("api/v1/user")
    suspend fun createUser(@Body user: User): User

    @PUT("api/v1/user/{user_id}")
    suspend fun updateUser(@Path("user_id") userId: String, @Body updatedUser: User): User

    @DELETE("api/v1/user/{user_id}")
    suspend fun deleteUser(@Path("user_id") userId: String)

    @POST("api/v1/user/request")
    suspend fun joinToCompany()

    @PUT("api/v1/user/promote/{user_id}")
    suspend fun promoteToManager(@Path("user_id") userId: String)
}
