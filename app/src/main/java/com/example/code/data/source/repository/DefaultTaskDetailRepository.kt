package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.TaskDetail
import com.example.code.data.source.remote.TaskDetailService

class DefaultTaskDetailRepository(
    private val appDatabase: AppDatabase,
    private val remoteDataSource: TaskDetailService
) {
    suspend fun getAllTaskDetails(forceUpdate: Boolean = false): List<TaskDetail> {
        return if (forceUpdate) {
            val taskDetails = remoteDataSource.getAllTaskDetails()
            appDatabase.taskDetailDao().deleteAllTaskDetails()
            taskDetails
        } else {
            appDatabase.taskDetailDao().getAllTaskDetails()
        }
    }

    suspend fun getTaskDetailById(taskDetailId: String): TaskDetail? {
        return appDatabase.taskDetailDao().getTaskDetailById(taskDetailId)
    }

    suspend fun createTaskDetail(
        tdName:String,
        tdDescription:String,
        taskId:String,
        tdStatus:Boolean
    ) {
        val newTaskDetail = remoteDataSource.createTaskDetail(
            tdName,
            tdDescription,
            taskId,
            tdStatus
        )
        appDatabase.taskDetailDao().insertTaskDetail(TaskDetail(
            tdId = newTaskDetail.tdId,
            tdName = newTaskDetail.tdName,
            tdDescription = newTaskDetail.tdDescription,
            taskId = taskId,
            tdStatus = tdStatus
        ))
    }

    suspend fun updateTaskDetail(taskDetail: TaskDetail) {
        val newTaskDetail = remoteDataSource.updateTaskDetail(taskDetail.tdId, taskDetail)
        appDatabase.taskDetailDao().insertTaskDetail(newTaskDetail)
    }

    suspend fun deleteTaskDetail(taskDetailId: String) {
        remoteDataSource.deleteTaskDetail(taskDetailId)
        appDatabase.taskDetailDao().deleteTaskDetailById(taskDetailId)
    }
}
