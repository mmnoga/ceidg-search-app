package pl.careaboutit.ceidgapp.api

import pl.careaboutit.ceidgapp.api.model.CompanyDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("firmy")
    suspend fun getCompanyData(
        @Query("nip") nip: String
    ): CompanyDataResponse

    @GET("firmy")
    suspend fun getCompaniesByPkdAndCity(
        @Query("pkd") pkd: String,
        @Query("miasto") city: String
    ): CompanyDataResponse
}