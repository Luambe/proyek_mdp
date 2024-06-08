package com.example.code.data.source.remote

import com.example.code.data.source.model.Task
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskService {
    @GET("api/v1/task")
    suspend fun getAllTasks(): List<Task>

    @GET("api/v1/task/{task_id}")
    suspend fun getTaskById(@Path("task_id") taskId: String): Task

    @POST("api/v1/task")
    suspend fun createTask(@Body task: Task): Task

    @PUT("api/v1/task/{task_id}")
    suspend fun updateTask(@Path("task_id") taskId: String, @Body updatedTask: Task): Task

    @DELETE("api/v1/task/{task_id}")
    suspend fun deleteTask(@Path("task_id") taskId: String)
}
