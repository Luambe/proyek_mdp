package com.example.code.data.source.remote

import com.example.code.data.source.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("api/v1/user")
    suspend fun getAllUsers(): List<User>

    @GET("api/v1/user/{user_id}")
    suspend fun getUserById(@Path("user_id") userId: String): User

    @GET("api/v1/user/{user_username}/{user_password}")
    suspend fun getUserByIdAndPassword(@Path("user_username") username: String, @Path("user_password") password: String): User

    @FormUrlEncoded
    @POST("api/v1/user")
    suspend fun createUser(
        @Field("user_name") username: String,
        @Field("user_username") userUsername: String,
        @Field("user_password") userPassword: String?,
        @Field("user_confirmation_password") userConfirmationPassword: String,
        @Field("user_phone") userPhone: String,
        @Field("user_email") userEmail: String,
        @Field("user_role") userRole: String
    ): User


    @PUT("api/v1/user/{user_id}")
    suspend fun updateUser(@Path("user_id") userId: String?, @Body updatedUser: User): User

    @DELETE("api/v1/user/{user_id}")
    suspend fun deleteUser(@Path("user_id") userId: String?)

    @POST("api/v1/user/request")
    suspend fun joinToCompany()

    @PUT("api/v1/user/promote/{user_id}")
    suspend fun promoteToManager(@Path("user_id") userId: String)
}
