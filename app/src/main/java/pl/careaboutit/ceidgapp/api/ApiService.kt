package pl.careaboutit.ceidgapp.api

import pl.careaboutit.ceidgapp.api.model.CompaniesDataResponse
import pl.careaboutit.ceidgapp.api.model.CompanyDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("firmy")
    suspend fun getCompanyData(
        @Query("nip") nip: String
    ): Response<CompaniesDataResponse>

    @GET("firmy")
    suspend fun getCompaniesByPkdAndCity(
        @Query("pkd") pkd: String,
        @Query("miasto") city: String
    ): Response<CompaniesDataResponse>

    @GET("firma")
    suspend fun getCompanyDetailsDataByCompanyId(
        @Path("id") id: String,
    ): Response<CompanyDataResponse>

    @GET("firma")
    suspend fun getCompanyDetailsData(
        @Query("nip") nip: String? = null,
        @Query("regon") regon: String? = null,
        @Query("ids") ids: List<String>? = null
    ): Response<CompanyDataResponse>

}