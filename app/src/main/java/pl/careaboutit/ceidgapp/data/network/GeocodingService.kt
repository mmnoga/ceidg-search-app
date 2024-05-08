package pl.careaboutit.ceidgapp.data.network

import pl.careaboutit.ceidgapp.data.model.GeocodingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
    @GET("maps/api/geocode/json")
    suspend fun getCoordinatesFromAddress(
        @Query("address") address: String,
        @Query("key") apiKey: String
    ): Response<GeocodingResponse>
}