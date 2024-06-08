package com.example.code.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.code.data.source.model.Announcement

@Dao
interface AnnouncementDao {
    @Query("SELECT * FROM announcements")
    suspend fun getAllAnnouncements(): List<Announcement>

    @Query("SELECT * FROM announcements WHERE announcement_id = :announcementId")
    suspend fun getAnnouncementById(announcementId: String): Announcement?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnnouncement(announcement: Announcement)

    @Query("DELETE FROM announcements")
    suspend fun deleteAllAnnouncements()

    @Query("DELETE FROM announcements WHERE announcement_id = :announcementId")
    suspend fun deleteAnnouncementById(announcementId: String)

    @Update
    suspend fun updateAnnouncement(announcement: Announcement)
}
