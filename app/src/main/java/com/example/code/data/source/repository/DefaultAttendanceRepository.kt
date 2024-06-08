package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.Attendance
import com.example.code.data.source.remote.AttendanceService

class DefaultAttendanceRepository(
    private val appDatabase: AppDatabase,
    private val remoteDataSource: AttendanceService
) {
    suspend fun getAllAttendances(forceUpdate: Boolean = false): List<Attendance> {
        return if (forceUpdate) {
            val attendances = remoteDataSource.getAllAttendances()
            appDatabase.attendanceDao().apply {
                deleteAllAttendances()
                for (attendance in attendances) {
                    insertAttendance(attendance)
                }
            }
            attendances
        } else {
            appDatabase.attendanceDao().getAllAttendances()
        }
    }

    suspend fun getAttendanceById(attendanceId: String): Attendance? {
        return appDatabase.attendanceDao().getAttendanceById(attendanceId)
    }

    suspend fun createAttendance(attendance: Attendance) {
        val newAttendance = remoteDataSource.createAttendance(attendance)
        appDatabase.attendanceDao().insertAttendance(newAttendance)
    }

    suspend fun updateAttendance(attendance: Attendance) {
        val newAttendance = remoteDataSource.updateAttendance(attendance.attendanceId, attendance)
        appDatabase.attendanceDao().insertAttendance(newAttendance)
    }

    suspend fun deleteAttendance(attendanceId: String) {
        remoteDataSource.deleteAttendance(attendanceId)
        appDatabase.attendanceDao().deleteAttendanceById(attendanceId)
    }
}
