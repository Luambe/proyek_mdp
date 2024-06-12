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
            appDatabase.taskDao().deleteAllTasks()
            for (task in tasks){
                appDatabase.taskDao().insertTask(Task(
                    taskId = task .taskId,
                    taskName = task.taskName,
                    taskDescription = task.taskDescription,
                    employeeId = task.employeeId,
                    managerId = task.managerId,
                    taskStatus = task.taskStatus
                ))
            }
            tasks
        } else {
            appDatabase.taskDao().getAllTasks()
        }
    }

    suspend fun getTaskById(taskId: String): Task? {
        val tasks = remoteDataSource.getAllTasks()
        appDatabase.taskDao().deleteAllTasks()
        for (task in tasks){
            appDatabase.taskDao().insertTask(Task(
                taskId = task.taskId,
                taskName = task.taskName,
                taskDescription = task.taskDescription,
                employeeId = task.employeeId,
                managerId = task.managerId,
                taskStatus = task.taskStatus
            ))
        }
        return appDatabase.taskDao().getTaskById(taskId)
    }

    suspend fun createTask(
        taskName: String,
        taskDescription: String,
        employeeId: String,
        managerId: String,
        taskStatus: Int
    ): Task? {
        val newTask = remoteDataSource.createTask(
            taskName,taskDescription,employeeId,managerId,taskStatus
        )

        appDatabase.taskDao().insertTask(Task(
            taskId = newTask.taskId,
            taskName = newTask.taskName,
            taskDescription = newTask.taskDescription,
            employeeId = newTask.employeeId,
            managerId = newTask.managerId,
            taskStatus = newTask.taskStatus
        ))

        return appDatabase.taskDao().getTaskById(newTask.taskId)
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
