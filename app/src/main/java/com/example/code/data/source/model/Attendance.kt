package com.example.code.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "attendances")
data class Attendance(
    @PrimaryKey
    @ColumnInfo(name = "attendance_id") val attendanceId: String,
    @ColumnInfo(name = "user_id") val userId: String?,
    @ColumnInfo(name = "attendance_status") val attendanceStatus: Int?
)
