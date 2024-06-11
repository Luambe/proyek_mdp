package com.example.code.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "taskdetails")
data class TaskDetail(
    @PrimaryKey
    @ColumnInfo(name = "td_id")
    @Json(name = "td_id") val tdId: String,

    @ColumnInfo(name = "td_name")
    @Json(name = "td_name") val tdName: String?,

    @ColumnInfo(name = "td_description")
    @Json(name = "td_description") val tdDescription: String?,

    @ColumnInfo(name = "task_id")
    @Json(name = "task_id") val taskId: String?,

    @ColumnInfo(name = "td_status")
    @Json(name = "td_status") val tdStatus: Boolean
)
