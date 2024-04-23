package pl.careaboutit.ceidgapp.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {

    // val token: String = BuildConfig.CEIDG_API_TOKEN
    val token: String = "eyJraWQiOiJjZWlkZyIsImFsZyI6IkhTNTEyIn0.eyJnaXZlbl9uYW1lIjoiTWFyY2luIiwicGVzZWwiOiI4MDA4MjExNDg1OSIsImlhdCI6MTcxMjE3NDY3NiwiZmFtaWx5X25hbWUiOiJOb2dhIiwiY2xpZW50X2lkIjoiVVNFUi04MDA4MjExNDg1OS1NQVJDSU4tTk9HQSJ9.yksC7ljMfDhKmj_tHsK1Z3zvIvgu5JZibq7I4p-YBQFc5uaWz5Iu_Gq2AsBjB4o-yiFUKKR6k3M2QWBGxFd2fQ"

    val baseUrl: String = "https://dane.biznes.gov.pl/api/ceidg/v2/"

    val okHttp: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(AuthInterceptor(token))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttp)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

}