package com.example.code.data.source.remote

import com.example.code.data.source.model.TaskDetail
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskDetailService {
    @GET("api/v1/taskdetail")
    suspend fun getAllTaskDetails(): List<TaskDetail>

    @GET("api/v1/taskdetail/{td_id}")
    suspend fun getTaskDetailById(@Path("td_id") taskDetailId: String): TaskDetail

    @FormUrlEncoded
    @POST("api/v1/taskdetail")
    suspend fun createTaskDetail(
        @Field("td_name") tdName: String,
        @Field("td_description") tdDescription: String,
        @Field("task_id") taskId: String,
        @Field("td_status") tdStatus: Boolean
    ): TaskDetail

    @PUT("api/v1/taskdetail/{td_id}")
    suspend fun updateTaskDetail(@Path("td_id") taskDetailId: String, @Body updatedTaskDetail: TaskDetail): TaskDetail

    @DELETE("api/v1/taskdetail/{td_id}")
    suspend fun deleteTaskDetail(@Path("td_id") taskDetailId: String)
}
