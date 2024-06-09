package com.example.code.data.source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "announcements")
data class Announcement(
    @PrimaryKey
    @ColumnInfo(name = "announcement_id")
    @Json(name = "announcement_id") val announcementId: String,

    @ColumnInfo(name = "announcement_body")
    @Json(name = "announcement_body") val announcementBody: String?
)
