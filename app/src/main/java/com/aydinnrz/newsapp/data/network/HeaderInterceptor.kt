package com.aydinnrz.newsapp.data.network

import com.aydinnrz.newsapp.BuildConfig
import com.aydinnrz.newsapp.domain.constants.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/**
 * An Interceptor that adds headers to api call
 */
class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().addHeaders()
        return chain.proceed(newRequest)
    }

    // add headers to okHttp request
    private fun Request.addHeaders() : Request {
        return newBuilder().apply {
            header(Constants.API_KEY, BuildConfig.API_KEY)
        }.build()
    }
}