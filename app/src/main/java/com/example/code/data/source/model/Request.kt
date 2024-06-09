package com.example.code.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "requests")
data class Request(
    @PrimaryKey
    @ColumnInfo(name = "request_id")
    @Json(name = "request_id") val requestId: String,

    @ColumnInfo(name = "user_id")
    @Json(name = "user_id") val userId: String?,

    @ColumnInfo(name = "company_id")
    @Json(name = "company_id") val companyId: String?,

    @ColumnInfo(name = "status")
    @Json(name = "status") val status: String?
)
