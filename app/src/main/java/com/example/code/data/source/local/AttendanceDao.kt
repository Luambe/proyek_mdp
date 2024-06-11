package com.example.code.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.code.data.source.model.Attendance

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM attendances")
    suspend fun getAllAttendances(): List<Attendance>

    @Query("SELECT * FROM attendances WHERE attendance_id = :attendanceId")
    suspend fun getAttendanceById(attendanceId: String): Attendance?

    @Query("SELECT * FROM attendances WHERE user_id = :userId")
    suspend fun getAttendanceByUserId(userId: String): Attendance?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: Attendance)

    @Query("DELETE FROM attendances")
    suspend fun deleteAllAttendances()

    @Query("DELETE FROM attendances WHERE attendance_id = :attendanceId")
    suspend fun deleteAttendanceById(attendanceId: String)
}
