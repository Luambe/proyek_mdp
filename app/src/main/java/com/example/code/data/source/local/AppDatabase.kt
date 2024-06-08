package com.example.code.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.code.data.source.model.*

@Database(
    entities = [Announcement::class, Attendance::class, Company::class, Request::class, Task::class, TaskDetail::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun announcementDao(): AnnouncementDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun companyDao(): CompanyDao
    abstract fun requestDao(): RequestDao
    abstract fun taskDao(): TaskDao
    abstract fun taskDetailDao(): TaskDetailDao
    abstract fun userDao(): UserDao

}
