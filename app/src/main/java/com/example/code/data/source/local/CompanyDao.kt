package com.example.code.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.code.data.source.model.Company

@Dao
interface CompanyDao {
    @Query("SELECT * FROM companies")
    suspend fun getAllCompanies(): List<Company>

    @Query("SELECT * FROM companies WHERE company_id = :companyId")
    suspend fun getCompanyById(companyId: String): Company?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(company: Company)

    @Query("DELETE FROM companies")
    fun deleteAllCompanies()

    @Query("DELETE FROM companies WHERE company_id = :companyId")
    suspend fun deleteCompanyById(companyId: String)

    @Insert
    fun insertMany(companies:List<Company>)
}
