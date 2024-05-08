package pl.careaboutit.ceidgapp.data.network

import pl.careaboutit.ceidgapp.data.model.CompanyDetailsResponse
import pl.careaboutit.ceidgapp.data.model.CompanyListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface CeidgService {

    @GET("firmy")
    suspend fun getCompanyListByFilters(
        @QueryMap filters: Map<String, String>
    ): Response<CompanyListResponse>

    @GET("firma/{id}")
    suspend fun getCompanyDetailsById(
        @Path("id") id: String
    ): Response<CompanyDetailsResponse>

}