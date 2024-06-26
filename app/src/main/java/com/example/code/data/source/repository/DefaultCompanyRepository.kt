package com.example.code.data.source.repository

import com.example.code.data.source.local.AppDatabase
import com.example.code.data.source.model.Announcement
import com.example.code.data.source.model.Company
import com.example.code.data.source.model.User
import com.example.code.data.source.remote.CompanyService
import kotlinx.coroutines.awaitAll
import java.security.PrivateKey

class DefaultCompanyRepository(
    private val localDataSource: AppDatabase,
    private val remoteDataSource: CompanyService
) {
    suspend fun getAllCompanies(forceUpdate: Boolean = false): List<Company> {
        return if (forceUpdate){
            val companies = remoteDataSource.getAllCompanies()
            localDataSource.companyDao().deleteAllCompanies()
            for (company in companies) {
                localDataSource.companyDao().insertCompany(Company(
                    companyId = company.companyId,
                    companyName = company.companyName,
                    ownerId = company.ownerId,
                    privateKey = company.privateKey,
                    announcement = company.announcement
                ))
            }
            companies
        }
        else{
            remoteDataSource.getAllCompanies()
        }
    }

    suspend fun getCompanyById(companyId: String): Company?{
        println("masuk get company 1")
        val companies = remoteDataSource.getAllCompanies()
        println("masuk get company 2")
        for (company in companies) {
            localDataSource.companyDao().insertCompany(Company(
                companyId = company.companyId,
                companyName = company.companyName,
                ownerId = company.ownerId,
                privateKey = company.privateKey,
                announcement = company.announcement
            ))
        }
        println(localDataSource.companyDao().getCompanyById(companyId))
        return localDataSource.companyDao().getCompanyById(companyId)
    }

    suspend fun createCompany(
        companyName: String,
        ownerId: String,
        privateKey: String
    ) {
        val newCompany = remoteDataSource.createCompany(
            companyName,
            ownerId,
            privateKey
        )

        val company = Company(
            companyId = newCompany.companyId,
            companyName = newCompany.companyName,
            ownerId = newCompany.ownerId,
            privateKey = newCompany.privateKey,
            announcement = newCompany.announcement
        )

        localDataSource.companyDao().insertCompany(company)
    }

    suspend fun updateCompany(companyId: String,companyName: String, ownerId: String, privateKey: String,announcement: String): Company? {
        val newCompany = remoteDataSource.updateCompany(companyId,companyName, ownerId, privateKey, announcement)

        return localDataSource.companyDao().getCompanyById(newCompany.companyId)
    }

    suspend fun deleteCompany(companyId: String) {
        remoteDataSource.deleteCompany(companyId)
        localDataSource.companyDao().deleteCompanyById(companyId)
    }

    suspend fun getEmployeeFromCompany(companyId: String): List<User>{
        println(companyId)

        return remoteDataSource.getEmployeeFromCompany(companyId)

    }
}
