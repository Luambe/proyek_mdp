package com.example.code.data.source.repository

import com.example.code.data.source.local.AnnouncementDao
import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.Announcement
import com.example.code.data.source.remote.AnnouncementService

class DefaultAnnouncementRepository(
    private val appDatabase: AppDatabase,
    private val announcementService: AnnouncementService
) {
    suspend fun getAllAnnouncements(forceUpdate: Boolean = false): List<Announcement> {
        return if (forceUpdate) {
            val announcements = announcementService.getAllAnnouncements()
            appDatabase.announcementDao().apply {
                deleteAllAnnouncements()
                announcements.forEach { announcement ->
                    insertAnnouncement(announcement)
                }
            }
            announcements
        } else {
            appDatabase.announcementDao().getAllAnnouncements()
        }
    }

    suspend fun getAnnouncementById(announcementId: String): Announcement? {
        return appDatabase.announcementDao().getAnnouncementById(announcementId)
    }

    suspend fun createAnnouncement(announcement: Announcement) {
        announcementService.createAnnouncement(announcement)
    }

    suspend fun updateAnnouncement(announcementId: String, updatedAnnouncement: Announcement) {
        announcementService.updateAnnouncement(announcementId, updatedAnnouncement)
        appDatabase.announcementDao().updateAnnouncement(updatedAnnouncement)
    }

    suspend fun deleteAnnouncement(announcementId: String) {
        announcementService.deleteAnnouncement(announcementId)
        appDatabase.announcementDao().deleteAnnouncementById(announcementId)
    }
}
