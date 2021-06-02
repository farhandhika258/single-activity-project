package com.example.myapplication.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

class CryptoApiBuilder @Inject constructor() {

    fun buildApi(): CryptoApi {
        val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(CRYPTO_BASE_URL)
                .build()
        return builder.create(CryptoApi::class.java)
    }
}