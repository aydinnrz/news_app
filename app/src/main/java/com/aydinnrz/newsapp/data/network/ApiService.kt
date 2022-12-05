package com.aydinnrz.newsapp.data.network

import com.aydinnrz.newsapp.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/everything")
    suspend fun searchNews(@Query("page") page : Int, @Query("pageSize") pageSize : Int, @Query("q") query:String ): Response<NewsResponse>

}