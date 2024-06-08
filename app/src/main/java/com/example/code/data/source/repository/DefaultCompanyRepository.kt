package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.Company
import com.example.code.data.source.remote.CompanyService

class DefaultCompanyRepository(
    private val appDatabase: AppDatabase,
    private val remoteDataSource: CompanyService
) {
    suspend fun getAllCompanies(forceUpdate: Boolean = false): List<Company> {
        return if (forceUpdate) {
            val companies = remoteDataSource.getAllCompanies()
            appDatabase.companyDao().apply {
                deleteAllCompanies()
                for (company in companies) {
                    insertCompany(company)
                }
            }
            companies
        } else {
            appDatabase.companyDao().getAllCompanies()
        }
    }

    suspend fun getCompanyById(companyId: String): Company? {
        return appDatabase.companyDao().getCompanyById(companyId) ?: remoteDataSource.getCompanyById(companyId)
    }

    suspend fun createCompany(company: Company) {
        val newCompany = remoteDataSource.createCompany(company)
        appDatabase.companyDao().insertCompany(newCompany)
    }

    suspend fun updateCompany(company: Company) {
        val newCompany = remoteDataSource.updateCompany(company.companyId, company)
        appDatabase.companyDao().insertCompany(newCompany)
    }

    suspend fun deleteCompany(companyId: String) {
        remoteDataSource.deleteCompany(companyId)
        appDatabase.companyDao().deleteCompanyById(companyId)
    }
}
