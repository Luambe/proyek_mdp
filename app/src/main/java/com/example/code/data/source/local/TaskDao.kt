package com.example.code.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.code.data.source.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE task_id = :taskId")
    suspend fun getTaskById(taskId: String): Task?

    @Insert()
    suspend fun insertTask(task: Task)

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    @Query("DELETE FROM tasks WHERE task_id = :taskId")
    suspend fun deleteTaskById(taskId: String)
}
