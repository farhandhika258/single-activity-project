package com.example.myapplication.services

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val CRYPTO_BASE_URL: String = "https://min-api.cryptocompare.com"

interface CryptoApi {
    @GET("/data/top/totaltoptiervolfull")
    fun fetchCodeRepos(@Query("limit") limit: Int,
                       @Query("tsym") tsym: String): Observable<CryptoReceiver>
}