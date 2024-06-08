package com.example.code.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.code.data.source.model.TaskDetail

@Dao
interface TaskDetailDao {
    @Query("SELECT * FROM taskdetails")
    suspend fun getAllTaskDetails(): List<TaskDetail>

    @Query("SELECT * FROM taskdetails WHERE td_id = :taskDetailId")
    suspend fun getTaskDetailById(taskDetailId: String): TaskDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskDetail(taskDetail: TaskDetail)

    @Query("DELETE FROM taskdetails")
    suspend fun deleteAllTaskDetails()

    @Query("DELETE FROM taskdetails WHERE td_id = :taskDetailId")
    suspend fun deleteTaskDetailById(taskDetailId: String)
}
