package com.example.code.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "companies")
data class Company(
    @PrimaryKey
    @ColumnInfo(name = "company_id")
    @Json(name = "company_id") val companyId: String,

    @ColumnInfo(name = "company_name")
    @Json(name = "company_name") val companyName: String?,

    @ColumnInfo(name = "owner_id")
    @Json(name = "owner_id") val ownerId: String?,

    @ColumnInfo(name = "private_key")
    @Json(name = "private_key") val privateKey: String?,

    @ColumnInfo(name = "announcement")
    @Json(name = "announcement") val announcement:String?
)
