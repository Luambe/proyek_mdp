package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.Company
import com.example.code.data.source.remote.CompanyService

class DefaultCompanyRepository(
    private val localDataSource: AppDatabase,
    private val remoteDataSource: CompanyService
) {
    suspend fun getAllCompanies(forceUpdate: Boolean = false): List<Company> {

            return remoteDataSource.getAllCompanies()
    }

    suspend fun getCompanyById(companyId: String): Company? {
        return localDataSource.companyDao().getCompanyById(companyId) ?: remoteDataSource.getCompanyById(companyId)
    }

    suspend fun createCompany(company: Company) {
        val newCompany = remoteDataSource.createCompany(company)
        localDataSource.companyDao().insertCompany(newCompany)
    }

    suspend fun updateCompany(company: Company) {
        val newCompany = remoteDataSource.updateCompany(company.companyId, company)
        localDataSource.companyDao().insertCompany(newCompany)
    }

    suspend fun deleteCompany(companyId: String) {
        remoteDataSource.deleteCompany(companyId)
        localDataSource.companyDao().deleteCompanyById(companyId)
    }
}
