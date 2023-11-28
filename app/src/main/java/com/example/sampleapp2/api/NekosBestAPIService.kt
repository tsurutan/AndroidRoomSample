package com.example.sampleapp2.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

data class Neko(
    @SerializedName("artist_name") val artistName: String,
    @SerializedName("url") val url: String,
)

data class NekosResponse(
    val results: List<Neko>
)

interface NekosBestAPIService {
    @GET("neko")
    suspend fun listNekos(@Query("amount") amount: Int): Response<NekosResponse>
}