package com.example.sampleapp2.api

import retrofit2.Response
import retrofit2.http.GET

interface ShibeAPIService {
    @GET("shibes?count=50&urls=true&httpsUrls=true")
    suspend fun listShibes(): Response<Array<String>>
}