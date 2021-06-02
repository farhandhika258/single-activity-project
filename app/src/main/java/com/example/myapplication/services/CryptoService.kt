package com.example.myapplication.services

import io.reactivex.Observable
import javax.inject.Inject

class CryptoService @Inject constructor(
        private val cryptoApiBuilder: CryptoApiBuilder
) {

    private val cryptoApi: CryptoApi = cryptoApiBuilder.buildApi()

    fun fetchPrices(limit: Int, tsym: String): Observable<CryptoReceiver> {
        return cryptoApi.fetchCodeRepos(limit, tsym)
    }
}