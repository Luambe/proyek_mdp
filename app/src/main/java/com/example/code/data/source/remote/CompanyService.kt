package com.example.code.data.source.remote

import com.example.code.data.source.model.Company
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CompanyService {
    @GET("api/v1/company")
    suspend fun getAllCompanies(): List<Company>

    @GET("api/v1/company/{company_id}")
    suspend fun getCompanyById(@Path("company_id") companyId: String): Company

    @POST("api/v1/company")
    suspend fun createCompany(@Body company: Company): Company

    @PUT("api/v1/company/{company_id}")
    suspend fun updateCompany(@Path("company_id") companyId: String, @Body updatedCompany: Company): Company

    @DELETE("api/v1/company/{company_id}")
    suspend fun deleteCompany(@Path("company_id") companyId: String)
}
