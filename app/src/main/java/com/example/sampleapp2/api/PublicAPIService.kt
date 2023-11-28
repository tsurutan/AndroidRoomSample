package com.example.sampleapp2.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

data class Entry(
    @SerializedName("Description") val description: String,
    @SerializedName("Category") val category: String,
    @SerializedName("Link") val link: String
)

data class EntriesResponse(
    val count: Int,
    val entries: List<Entry>
)

interface PublicAPIService {
    @GET("entries")
    suspend fun listEntries(): Response<EntriesResponse>
}