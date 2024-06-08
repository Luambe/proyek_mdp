package com.example.code.data.source.remote

import com.example.code.data.source.model.Request
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RequestService {
    @GET("api/v1/request")
    suspend fun getAllRequests(): List<Request>

    @GET("api/v1/request/{request_id}")
    suspend fun getRequestById(@Path("request_id") requestId: String): Request

    @POST("api/v1/request")
    suspend fun createRequest(@Body request: Request): Request

    @PUT("api/v1/request/{request_id}")
    suspend fun updateRequest(@Path("request_id") requestId: String, @Body updatedRequest: Request): Request

    @DELETE("api/v1/request/{request_id}")
    suspend fun deleteRequest(@Path("request_id") requestId: String)
}
