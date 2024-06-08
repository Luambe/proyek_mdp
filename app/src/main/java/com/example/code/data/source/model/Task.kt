package com.example.code.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    @ColumnInfo(name = "task_id") val taskId: String,
    @ColumnInfo(name = "task_name") val taskName: String?,
    @ColumnInfo(name = "task_description") val taskDescription: String?,
    @ColumnInfo(name = "employee_id") val employeeId: String?,
    @ColumnInfo(name = "manager_id") val managerId: String?,
    @ColumnInfo(name = "task_status") val taskStatus: Int?
)
