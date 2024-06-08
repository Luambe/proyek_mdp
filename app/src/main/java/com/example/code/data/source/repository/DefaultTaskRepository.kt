package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.Task
import com.example.code.data.source.remote.TaskService

class DefaultTaskRepository(
    private val appDatabase: AppDatabase,
    private val remoteDataSource: TaskService
) {
    suspend fun getAllTasks(forceUpdate: Boolean = false): List<Task> {
        return if (forceUpdate) {
            val tasks = remoteDataSource.getAllTasks()
            appDatabase.taskDao().apply {
                deleteAllTasks()
                for (task in tasks) {
                    insertTask(task)
                }
            }
            tasks
        } else {
            appDatabase.taskDao().getAllTasks()
        }
    }

    suspend fun getTaskById(taskId: String): Task? {
        return appDatabase.taskDao().getTaskById(taskId)
    }

    suspend fun createTask(task: Task) {
        val newTask = remoteDataSource.createTask(task)
        appDatabase.taskDao().insertTask(newTask)
    }

    suspend fun updateTask(task: Task) {
        val newTask = remoteDataSource.updateTask(task.taskId, task)
        appDatabase.taskDao().insertTask(newTask)
    }

    suspend fun deleteTask(taskId: String) {
        remoteDataSource.deleteTask(taskId)
        appDatabase.taskDao().deleteTaskById(taskId)
    }
}
