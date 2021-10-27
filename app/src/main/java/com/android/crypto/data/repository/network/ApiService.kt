package com.android.crypto.data.repository.network

import com.android.crypto.domain.entites.CoinEntity
import com.android.crypto.domain.entites.CoinsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v1/coins")
   suspend fun getCoins():Response<List<CoinsEntity>>

   @GET("v1/coins/{coinId}")
   suspend fun getCoin(@Path("coinId") coinId:String): Response<CoinEntity>
}