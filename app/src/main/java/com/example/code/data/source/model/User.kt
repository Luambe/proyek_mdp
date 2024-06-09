package com.example.code.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    @Json(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "user_name")
    @Json(name = "user_name")
    val userName: String?,

    @ColumnInfo(name = "user_username")
    @Json(name = "user_username")
    val userUsername: String?,

    @ColumnInfo(name = "user_password")
    @Json(name = "user_password")
    val userPassword: String?,

    @ColumnInfo(name = "user_phone")
    @Json(name = "user_phone")
    val userPhone: String?,

    @ColumnInfo(name = "user_email")
    @Json(name = "user_email")
    val userEmail: String?,

    @ColumnInfo(name = "company_id")
    @Json(name = "company_id")
    val companyId: String? = null,

    @ColumnInfo(name = "user_role")
    @Json(name = "user_role")
    val userRole: String?
)
