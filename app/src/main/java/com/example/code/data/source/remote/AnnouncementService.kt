package com.example.code.data.source.remote

import com.example.code.data.source.model.Announcement
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AnnouncementService {
    @GET("api/v1/announcement")
    suspend fun getAllAnnouncements(): List<Announcement>

    @GET("api/v1/announcement/{announcement_id}")
    suspend fun getAnnouncementById(@Path("announcement_id") announcementId: String): Announcement

    @POST("api/v1/announcement")
    suspend fun createAnnouncement(@Body announcement: Announcement): Announcement

    @PUT("api/v1/announcement/{announcement_id}")
    suspend fun updateAnnouncement(@Path("announcement_id") announcementId: String, @Body updatedAnnouncement: Announcement): Announcement

    @DELETE("api/v1/announcement/{announcement_id}")
    suspend fun deleteAnnouncement(@Path("announcement_id") announcementId: String)
}
