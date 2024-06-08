package com.example.code.data.source.remote

import com.example.code.data.source.model.Attendance
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AttendanceService {
    @GET("api/v1/attendance")
    suspend fun getAllAttendances(): List<Attendance>

    @GET("api/v1/attendance/{attendance_id}")
    suspend fun getAttendanceById(@Path("attendance_id") attendanceId: String): Attendance

    @POST("api/v1/attendance")
    suspend fun createAttendance(@Body attendance: Attendance): Attendance

    @PUT("api/v1/attendance/{attendance_id}")
    suspend fun updateAttendance(@Path("attendance_id") attendanceId: String, @Body updatedAttendance: Attendance): Attendance

    @DELETE("api/v1/attendance/{attendance_id}")
    suspend fun deleteAttendance(@Path("attendance_id") attendanceId: String)
}
