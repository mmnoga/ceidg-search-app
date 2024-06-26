package pl.careaboutit.ceidgapp.data.repository

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.careaboutit.ceidgapp.BuildConfig
import pl.careaboutit.ceidgapp.data.network.CeidgInterceptor
import pl.careaboutit.ceidgapp.data.network.CeidgService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit


class NetworkModule {

    val token: String = BuildConfig.CEIDG_API_KEY

    val baseUrl: String = "https://dane.biznes.gov.pl/api/ceidg/v2/"

    val okHttp: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(CeidgInterceptor(token))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttp)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val ceidgService: CeidgService = retrofit.create(CeidgService::class.java)
}