package com.example.code.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.code.data.source.model.Request

@Dao
interface RequestDao {
    @Query("SELECT * FROM requests")
    suspend fun getAllRequests(): List<Request>

    @Query("SELECT * FROM requests WHERE request_id = :requestId")
    suspend fun getRequestById(requestId: String): Request?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: Request)

    @Query("DELETE FROM requests")
    suspend fun deleteAllRequests()

    @Query("DELETE FROM requests WHERE request_id = :requestId")
    suspend fun deleteRequestById(requestId: String)
}

