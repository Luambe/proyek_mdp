package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.Request
import com.example.code.data.source.remote.RequestService

class DefaultRequestRepository(
    private val appDatabase: AppDatabase,
    private val remoteDataSource: RequestService
) {
    suspend fun getAllRequests(forceUpdate: Boolean = false): List<Request> {
        return if (forceUpdate) {
            val requests = remoteDataSource.getAllRequests()
            appDatabase.requestDao().apply {
                deleteAllRequests()
                for (request in requests) {
                    insertRequest(request)
                }
            }
            requests
        } else {
            appDatabase.requestDao().getAllRequests()
        }
    }

    suspend fun getRequestById(requestId: String): Request? {
        return appDatabase.requestDao().getRequestById(requestId)
    }

    suspend fun createRequest(request: Request) {
        val newRequest = remoteDataSource.createRequest(request)
        appDatabase.requestDao().insertRequest(newRequest)
    }

    suspend fun updateRequest(request: Request) {
        val newRequest = remoteDataSource.updateRequest(request.requestId, request)
        appDatabase.requestDao().insertRequest(newRequest)
    }

    suspend fun deleteRequest(requestId: String) {
        remoteDataSource.deleteRequest(requestId)
        appDatabase.requestDao().deleteRequestById(requestId)
    }
}
