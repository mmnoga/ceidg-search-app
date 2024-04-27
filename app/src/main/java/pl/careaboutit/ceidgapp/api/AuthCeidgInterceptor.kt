package pl.careaboutit.ceidgapp.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthCeidgInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}