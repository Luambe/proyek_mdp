package com.example.code.data.source.remote

import com.example.code.data.source.model.Company
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CompanyService {
    @GET("api/v1/company")
    suspend fun getAllCompanies(): List<Company>

    @GET("api/v1/company/{company_id}")
    suspend fun getCompanyById(@Path("company_id") companyId: String): Company

    @FormUrlEncoded
    @POST("api/v1/company")
    suspend fun createCompany(
        @Field("company_name") companyName: String,
        @Field("owner_id") ownerId: String,
        @Field("private_key") privateKey: String
    ) : Company

    @PUT("api/v1/company/{company_id}")
    suspend fun updateCompany(@Path("company_id") companyId: String, @Body updatedCompany: Company): Company

    @DELETE("api/v1/company/{company_id}")
    suspend fun deleteCompany(@Path("company_id") companyId: String)

    @DELETE("api/v1/company/")
    fun deleteAllCompany()
}
